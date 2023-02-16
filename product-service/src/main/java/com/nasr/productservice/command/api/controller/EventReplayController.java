package com.nasr.productservice.command.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nasr.productservice.core.constant.ConstantField.PRODUCT_PROCESSING_GROUP;

@RestController
@Slf4j
@RequestMapping("/managements")
public class EventReplayController {
    @Autowired
    private EventProcessingConfiguration eventProcessingConfiguration;
    @PostMapping("/eventProcessor/reset")
    public ResponseEntity<?> replayEvents() throws Exception {

        try {

            eventProcessingConfiguration
                    .eventProcessor(PRODUCT_PROCESSING_GROUP, TrackingEventProcessor.class)
                    .ifPresent(trackingEventProcessor -> {
                        trackingEventProcessor.shutDown();
                        trackingEventProcessor.resetTokens();
                        trackingEventProcessor.start();

                    });
            return ResponseEntity.ok("event processor with name : "+PRODUCT_PROCESSING_GROUP+" it was reset !");

        } catch (Exception e) {

            String message = String.format("dont find any tracking event processor with name : [%s] in this instance", PRODUCT_PROCESSING_GROUP);
            log.error(message);

            throw new Exception(message);
        }
    }
}
