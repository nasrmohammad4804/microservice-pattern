package com.nasr.productservice.core.mapper;

import com.nasr.productservice.core.event.ProductCreatedEvent;
import com.nasr.productservice.query.api.data.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper{

    Product convertEventToEntity(ProductCreatedEvent event);
}
