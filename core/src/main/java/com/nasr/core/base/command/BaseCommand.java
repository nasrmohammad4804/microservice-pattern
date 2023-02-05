package com.nasr.core.base.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BaseCommand <ID extends Serializable>{

    @TargetAggregateIdentifier
    protected final ID id;
}
