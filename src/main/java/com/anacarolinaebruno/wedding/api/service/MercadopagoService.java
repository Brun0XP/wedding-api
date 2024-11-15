package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.Payment;
import com.anacarolinaebruno.wedding.api.model.entity.Product;
import com.anacarolinaebruno.wedding.api.repository.PaymentRepository;
import com.anacarolinaebruno.wedding.api.repository.ProductRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.merchantorder.MerchantOrderPayment;
import com.mercadopago.resources.preference.Preference;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MercadopagoService {

    private final Logger logger = LoggerFactory.getLogger(MercadopagoService.class);

    private final PaymentRepository paymentRepository;

    private final ProductRepository productRepository;

    @Value("${application.mercadopago.access-token}")
    private String mercadopagoAccessToken;

    @Value("${application.api-url}")
    private String apiUrl;

    private final PaymentService paymentService;

    public Preference createPreference(String firstName, String lastName, List<Product> products) throws MPException, MPApiException, NoSuchElementException {
        MercadoPagoConfig.setAccessToken(mercadopagoAccessToken);

        Payment payment = paymentService.createNewPayment(firstName, lastName);
        payment.setProduct(products.get(0));

        List<PreferenceItemRequest> items = new ArrayList<>();
        products.forEach(product -> {
            items.add(PreferenceItemRequest.builder()
                    .id(product.getId().toString())
                    .title(product.getName())
                    .description(product.getDescription())
                    .quantity(1)
                    .currencyId("BRL")
                    .unitPrice(BigDecimal.valueOf(product.getPrice()))
                    .build());
        });

        PreferencePayerRequest payer = PreferencePayerRequest.builder()
                .name(firstName)
                .surname(lastName)
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .expires(true)
                .payer(payer)
                .dateOfExpiration(OffsetDateTime.now().plusDays(1))
                .notificationUrl(String.format("%s/checkout/mercadopago/confirmation?source_news=ipn", apiUrl))
                .statementDescriptor("Casamento")
                .externalReference(payment.getId().toString())
                .paymentMethods(PreferencePaymentMethodsRequest.builder()
                        .excludedPaymentMethods(List.of(PreferencePaymentMethodRequest.builder()
                                .id("bolbradesco")
                                .build()))
                        .excludedPaymentTypes(List.of(PreferencePaymentTypeRequest.builder()
                                .id("bolbradesco")
                                .build()))
                        .build())
                .build();


        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        payment.setPreferenceId(preference.getId());
        paymentRepository.save(payment);

        return preference;
    }

    @Transactional
    public void processNotification(String topic, Long id) throws MPException, MPApiException, ValidationException {
        logger.info("Processando webhook Mercadopago: {}, id: {}", topic, id);

        MercadoPagoConfig.setAccessToken(mercadopagoAccessToken);
        com.mercadopago.resources.payment.Payment payment;
        MerchantOrder merchantOrder;

        if (topic.equals("payment")) {
            if (paymentRepository.findById(id).isPresent()) {
                logger.info("Pagamento já cadastrado, processamento cancelado: {}", id);
                throw new ValidationException("Pagamento já cadastrado, processamento cancelado");
            }
            payment = new PaymentClient().get(id);
            merchantOrder = new MerchantOrderClient().get(payment.getOrder().getId());
        } else {
            payment = null;
            merchantOrder = null;
        }

        if (Objects.isNull(merchantOrder)) return;

        BigDecimal paidAmount = merchantOrder.getPayments().stream()
                .filter(merchantOrderPayment -> merchantOrderPayment.getStatus().equals("approved"))
                .map(MerchantOrderPayment::getTransactionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (paidAmount.compareTo(merchantOrder.getTotalAmount()) >= 0) {
            paymentRepository.findByPreferenceId(merchantOrder.getPreferenceId()).ifPresentOrElse(currentPayment -> {
                currentPayment.setEmail(payment.getPayer().getEmail());
                currentPayment.setIpAddress(payment.getAdditionalInfo().getIpAddress());
                currentPayment.setTransactionAmount(payment.getTransactionAmount().doubleValue());
                currentPayment.setReceivedAmount(payment.getTransactionDetails().getNetReceivedAmount().doubleValue());
                currentPayment.setPaymentMethod(payment.getPaymentMethodId());
                currentPayment.setOrderId(payment.getOrder().getId());
                currentPayment.setStatus(Payment.Status.valueOf(payment.getStatus().toUpperCase()));
                currentPayment.setDateCreated(payment.getDateApproved().toLocalDateTime());
                currentPayment.setDateApproved(payment.getDateCreated().toLocalDateTime());
                currentPayment.setLastModified(payment.getDateLastUpdated().toLocalDateTime());
            }, () -> {
                logger.info("Preferencia não encontrada, pagamento não registrado", id);
            });
        }
    }

}
