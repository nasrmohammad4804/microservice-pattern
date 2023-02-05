package com.nasr.core.base.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class BaseEvent <ID extends Serializable>{

    protected final ID id;
}
