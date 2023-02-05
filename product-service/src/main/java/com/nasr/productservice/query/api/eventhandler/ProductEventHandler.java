package com.nasr.productservice.query.api.eventhandler;

import com.nasr.core.event.ProductReservedEvent;
import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.core.event.ProductCreatedEvent;
import com.nasr.productservice.query.api.data.Product;
import com.nasr.productservice.core.mapper.ProductMapper;
import com.nasr.productservice.query.api.service.ProductQueryService;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.productservice.core.constant.ConstantField.PRODUCT_PROCESSING_GROUP;


@Component
@ProcessingGroup(PRODUCT_PROCESSING_GROUP)
public class ProductEventHandler {


    @Autowired
    private ProductQueryService productQueryService;

    @Autowired
    private ProductMapper productMapper;

    @EventHandler
    public void on(ProductCreatedEvent event)  {
        Product product = productMapper.convertEventToEntity(event);


        productQueryService.save(product);
    }

    @EventHandler
    public void on(ProductReservedEvent event){
            productQueryService.reduceProductQuantity(
                    new OrderDetailData(event.getProductId(),event.getQuantity())
            );
    }

}
