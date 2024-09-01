package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.request.CreateCheckoutRequest;
import com.anacarolinaebruno.wedding.api.dto.response.CheckoutResponse;
import com.anacarolinaebruno.wedding.api.service.CheckoutService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping("checkout")
@RestController
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("create")
    public CheckoutResponse createCheckout(@RequestBody CreateCheckoutRequest request) throws MPException, MPApiException, NoSuchElementException {
        return checkoutService.createCheckout(request);
    }

    @PostMapping("mercadopago/confirmation")
    public CheckoutResponse mercadopagoConfirmation(@RequestBody CreateCheckoutRequest request) throws MPException, MPApiException, NoSuchElementException {
        return checkoutService.createCheckout(request);
    }

}
