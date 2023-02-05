package com.nasr.productservice.query.api.projections;

import com.nasr.productservice.query.api.dto.ProductResponseDto;
import com.nasr.productservice.query.api.queries.GetProductByIdQuery;
import com.nasr.productservice.query.api.queries.GetProductsQuery;
import com.nasr.productservice.query.api.service.ProductQueryService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/*
* we can call this class as ProductQueryHandler but we prefer to use ProductProjection and ProductAggregate
* instead of ProductCommandHandler and ProductQueryHandler
* */

@Component
public class ProductProjection {

    @Autowired
    private ProductQueryService productQueryService;

    @QueryHandler
    public ProductResponseDto handle(GetProductByIdQuery query) {
        return productQueryService.getProductById(query.getId());

    }

    @QueryHandler
    public List<ProductResponseDto> handle(GetProductsQuery query) {
        return productQueryService.getProducts();
    }
}
