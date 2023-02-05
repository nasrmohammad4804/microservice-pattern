package com.nasr.productservice.query.api.controller;

import com.nasr.productservice.query.api.dto.ProductResponseDto;
import com.nasr.productservice.query.api.queries.GetProductByIdQuery;
import com.nasr.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        ProductResponseDto dto = queryGateway
                .query(new GetProductByIdQuery(id), ProductResponseDto.class)
                .join();

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<ProductResponseDto> dtos = queryGateway.query(new GetProductsQuery(),
                        ResponseTypes.multipleInstancesOf(ProductResponseDto.class))
                .join();

        return ResponseEntity.ok(dtos);
    }
}
