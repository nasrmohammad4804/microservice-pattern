package com.nasr.productservice.query.api.service.impl;

import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.core.constant.ConstantMessage;
import com.nasr.productservice.query.api.data.Product;
import com.nasr.productservice.query.api.dto.ProductResponseDto;
import com.nasr.productservice.query.api.repository.ProductQueryRepository;
import com.nasr.productservice.query.api.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nasr.productservice.core.constant.ConstantMessage.*;

@Service
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {

    @Autowired
    private ProductQueryRepository repository;

    @Override
    public ProductResponseDto getProductById(String id) {
        return repository.findByProductId(id);
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        return repository.findAllProducts();
    }

    @Override
    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    @Transactional
    public void reduceProductQuantity(OrderDetailData orderDetailData) {
        repository.reduceQuantity(orderDetailData.getProductId(),orderDetailData.getOrderQuantity());
    }
}
