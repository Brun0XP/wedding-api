package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.request.CreateCheckoutRequest;
import com.anacarolinaebruno.wedding.api.dto.response.CheckoutResponse;
import com.anacarolinaebruno.wedding.api.service.CheckoutService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RequestMapping("checkout")
@RestController
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @GetMapping("create-checkout")
    public CheckoutResponse createCheckout(@RequestBody CreateCheckoutRequest request) throws MPException, MPApiException, NoSuchElementException {
        return checkoutService.createCheckout(request);
    }

}
