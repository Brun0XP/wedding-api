package com.anacarolinaebruno.wedding.api.repository;

import com.anacarolinaebruno.wedding.api.model.entity.Payment;
import com.anacarolinaebruno.wedding.api.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPreferenceId(String id);
}
