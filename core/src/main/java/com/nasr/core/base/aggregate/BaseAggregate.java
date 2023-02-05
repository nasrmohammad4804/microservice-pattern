package com.nasr.core.base.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAggregate<ID extends Serializable> {

    @AggregateIdentifier
    protected  ID id;
}
