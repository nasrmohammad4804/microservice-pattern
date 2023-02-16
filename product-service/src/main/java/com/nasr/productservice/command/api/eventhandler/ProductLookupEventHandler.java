package com.nasr.productservice.command.api.eventhandler;

import com.nasr.productservice.command.api.data.ProductLookup;
import com.nasr.productservice.command.api.service.ProductLookupService;
import com.nasr.productservice.core.event.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.productservice.core.constant.ConstantField.PRODUCT_PROCESSING_GROUP;

@Component
@ProcessingGroup(PRODUCT_PROCESSING_GROUP)
@Slf4j
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


    @ResetHandler
    public void reset(){
        log.info("all productLookup data deleted . because we want to replay event at beginning of event store for specific requirement !");
        productLookupService.deleteAll();
    }

}
