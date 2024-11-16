package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.request.CreateCheckoutRequest;
import com.anacarolinaebruno.wedding.api.dto.response.CheckoutResponse;
import com.anacarolinaebruno.wedding.api.service.CheckoutService;
import com.anacarolinaebruno.wedding.api.service.MercadopagoService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.NoSuchElementException;

@RequestMapping("checkout")
@RestController
@RequiredArgsConstructor
public class CheckoutController {



    private final CheckoutService checkoutService;

    private final MercadopagoService mercadopagoService;

    @PostMapping("create")
    public CheckoutResponse createCheckout(@RequestBody CreateCheckoutRequest request) throws MPException, MPApiException, NoSuchElementException, ValidationException {
        return checkoutService.createCheckout(request);
    }

    @PostMapping("mercadopago/confirmation")
    public void mercadopagoConfirmation(@RequestParam("id") Long id, @RequestParam("topic") String topic, @RequestParam("source_news") String sourceNews) throws MPException, MPApiException, NoSuchElementException, ValidationException {
        mercadopagoService.processNotification(topic, id);
    }

}
