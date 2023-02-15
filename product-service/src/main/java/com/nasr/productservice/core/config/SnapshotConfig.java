package com.nasr.productservice.core.config;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.nasr.productservice.core.constant.ConstantField.PRODUCT_SNAPSHOT_THRESHOLD;
import static com.nasr.productservice.core.constant.ConstantField.SNAPSHOT_TRIGGER_DEFINITION_BEAN_NAME;

@Configuration
public class SnapshotConfig {


    /*add snapshot for product domain at every 10 event stored in event store */

    @Bean(name = SNAPSHOT_TRIGGER_DEFINITION_BEAN_NAME)
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter){
        return new EventCountSnapshotTriggerDefinition(snapshotter, PRODUCT_SNAPSHOT_THRESHOLD);
    }
}
