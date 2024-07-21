package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.Product;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MercadopagoService {

    @Value("${application.mercadopago.access-token}")
    private String mercadopagoAccessToken;

    @Value("${application.api-url}")
    private String apiUrl;

    public Preference createPreference(List<Product> products) throws MPException, MPApiException, NoSuchElementException {
        MercadoPagoConfig.setAccessToken(mercadopagoAccessToken);

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

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .expires(true)
                .dateOfExpiration(OffsetDateTime.now().plusDays(1))
                .notificationUrl(String.format("%s/checkout/mercadopago/confirmation?source_news=ipn", apiUrl))
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
        return client.create(preferenceRequest);
    }
}
