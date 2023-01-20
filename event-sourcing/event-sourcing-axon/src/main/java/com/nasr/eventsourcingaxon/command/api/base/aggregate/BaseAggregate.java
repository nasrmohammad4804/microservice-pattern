package com.nasr.eventsourcingaxon.command.api.base.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseAggregate<T extends CharSequence> {

    @AggregateIdentifier
    protected T id;
}
