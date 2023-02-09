package com.nasr.core.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserPaymentDetail {

    private String customerId;
    private String firstName;
    private String lastName;
    private PaymentDetail paymentDetail;

    @Data
    @Builder
    public static class PaymentDetail{

        private String cardNumber;
        private String cvv2;
        private LocalDate expirationDate;
    }
}
