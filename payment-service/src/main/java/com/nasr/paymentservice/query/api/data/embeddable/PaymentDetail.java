package com.nasr.paymentservice.query.api.data.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDetail {

    public static final String CARD_NUMBER="card_number";
    public static final String CVV2="cvv2";
    public static final String EXPIRATION_DATE="expiration_date";


    @Column(name = CARD_NUMBER)
    private String cardNumber;

    @Column(name = CVV2)
    private String cvv2;

    @Column(name = EXPIRATION_DATE)
    private LocalDate expirationDate;
}
