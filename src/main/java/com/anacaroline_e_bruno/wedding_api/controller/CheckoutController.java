package com.anacaroline_e_bruno.wedding_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("checkout")
@RestController
public class CheckoutController {

    @GetMapping("hello")
    public String getHello() {
        return "Hello World!";
    }

}
