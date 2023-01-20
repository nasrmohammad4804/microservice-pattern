package com.nasr.eventsourcingaxon.command.api.base.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent <T>{

    protected T id;
}
