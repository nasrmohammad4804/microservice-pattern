package com.nasr.eventsourcingaxon.command.api.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity <T extends Serializable>{

    public static final String IS_DELETED="is_deleted";

    @Id
    protected T id;

    @Column(name = IS_DELETED)
    private Boolean isDeleted;

    public BaseEntity(T id) {
        this.id = id;
    }
}
