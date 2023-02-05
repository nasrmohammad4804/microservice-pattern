package com.nasr.productservice.query.api.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Setter
@Getter
public class GetProductByIdQuery {
    private final String id;
}
