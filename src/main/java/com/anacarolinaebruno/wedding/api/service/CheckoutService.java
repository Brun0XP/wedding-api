package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.Product;
import com.anacarolinaebruno.wedding.api.dto.request.CreateCheckoutRequest;
import com.anacarolinaebruno.wedding.api.dto.response.CheckoutResponse;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final ProductService productService;
    private final MercadopagoService mercadopagoService;

    public CheckoutResponse createCheckout(CreateCheckoutRequest checkoutRequest) throws MPException, MPApiException, ValidationException {
        List<Product> products = productService.getProducts(checkoutRequest.getProductsId());

        if (products.stream().anyMatch(Product::isInactive)) throw new ValidationException("Um dos items do seu carrinho não está mais disponível!");

        Preference preference =  mercadopagoService.createPreference(checkoutRequest.getFirstName(), checkoutRequest.getLastName(), products);

        return CheckoutResponse.builder().mercadopagoPreferenceId(preference.getId()).build();
    }

}
