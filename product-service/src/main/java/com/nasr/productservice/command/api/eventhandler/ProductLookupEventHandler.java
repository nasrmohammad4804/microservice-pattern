package com.nasr.productservice.command.api.eventhandler;

import com.nasr.core.event.ProductReservedEvent;
import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.command.api.data.ProductLookup;
import com.nasr.productservice.command.api.service.ProductLookupService;
import com.nasr.productservice.core.event.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.productservice.core.constant.ConstantField.*;

@Component
@ProcessingGroup(PRODUCT_PROCESSING_GROUP)
public class ProductLookupEventHandler {

    @Autowired
    private ProductLookupService productLookupService;

    @EventHandler
    public void on(ProductCreatedEvent event)  {

        ProductLookup lookup = ProductLookup.builder()
                .id(event.getId())
                .name(event.getName())
                .build();

        productLookupService.save(lookup);
    }


}
