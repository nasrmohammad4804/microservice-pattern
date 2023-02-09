package com.nasr.productservice.query.api.service;

import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.query.api.data.Product;
import com.nasr.productservice.query.api.dto.ProductResponseDto;

import java.util.List;

public interface ProductQueryService {

    ProductResponseDto getProductById(String id);

    List<ProductResponseDto> getProducts();

    void save(Product product);

    void reduceProductQuantity(OrderDetailData orderDetailData);

    void increaseProductQuantity(String id, Integer quantity);
}
