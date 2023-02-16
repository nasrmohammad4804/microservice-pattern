package com.nasr.productservice.query.api.eventhandler;

import com.nasr.core.event.ProductReservationCancelledEvent;
import com.nasr.core.event.ProductReservedEvent;
import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.core.event.ProductCreatedEvent;
import com.nasr.productservice.core.mapper.ProductMapper;
import com.nasr.productservice.query.api.data.Product;
import com.nasr.productservice.query.api.service.ProductQueryService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.nasr.productservice.core.constant.ConstantField.PRODUCT_PROCESSING_GROUP;


@Component
@ProcessingGroup(PRODUCT_PROCESSING_GROUP)
@Slf4j
public class ProductEventHandler {


    @Autowired
    private ProductQueryService productQueryService;

    @Autowired
    private ProductMapper productMapper;

    @EventHandler
    public void on(ProductCreatedEvent event, @Timestamp Instant eventDate) {
        Product product = productMapper.convertEventToEntity(event);
        product.setCreateAtDate(eventDate);

        productQueryService.save(product);
    }

    @EventHandler
    public void on(ProductReservedEvent event, @Timestamp Instant eventDate) {
        productQueryService.reduceProductQuantity(
                new OrderDetailData(event.getId(), event.getQuantity()),eventDate
        );
    }

    @EventHandler
    public void on(ProductReservationCancelledEvent event,@Timestamp Instant eventDate) {
        productQueryService.increaseProductQuantity(event.getId(), event.getQuantity(),eventDate);
    }

    /* this method whenever reset token prepared call(eventTrackingProcessor.resetTokens())
       and executed before event would replay(eventTrackingProcessor.start()) */
    @ResetHandler
    public void reset() {
        log.info("all product data deleted . because we want to replay event at beginning of event store for specific requirement !");
        productQueryService.deleteAll();
    }
}
