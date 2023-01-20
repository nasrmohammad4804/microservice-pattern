package com.nasr.eventsourcingaxon.command.api.base.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    protected T id;
}
