package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.Payment;
import com.anacarolinaebruno.wedding.api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createNewPayment(String firstName, String lastName) {
        return paymentRepository.save(Payment.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                .build());
    }
}
