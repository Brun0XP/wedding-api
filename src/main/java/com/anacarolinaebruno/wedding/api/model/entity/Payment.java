package com.anacarolinaebruno.wedding.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="payment_id")
    private Long paymentId;

    @Column(name = "preference_id")
    private String preferenceId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "received_amount")
    private Double receivedAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "approved_at")
    private LocalDateTime dateApproved;

    @Column(name = "created_at")
    private LocalDateTime dateCreated;

    @Column(name = "updated_at")
    private LocalDateTime lastModified;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "payment_status")
    private Status status;

    public enum Status {
        PENDING, APPROVED, COMPLETED
    }
}
