package com.nasr.core.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetUserPaymentDetailQuery {

    private final String userId;
}
