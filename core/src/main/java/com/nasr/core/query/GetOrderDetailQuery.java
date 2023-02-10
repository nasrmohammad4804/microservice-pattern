package com.nasr.core.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOrderDetailQuery {

    private String orderId;
}
