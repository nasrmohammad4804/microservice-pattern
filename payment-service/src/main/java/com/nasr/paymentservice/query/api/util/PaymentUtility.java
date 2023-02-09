package com.nasr.paymentservice.query.api.util;

import com.nasr.paymentservice.core.enumeration.PaymentType;

import static com.nasr.core.model.UserPaymentDetail.PaymentDetail;


public class PaymentUtility {

    public static void pay(PaymentDetail paymentDetail, PaymentType type) {

        /* using third party payment library like stripe for pay with paymentDetail & type */
    }
}
