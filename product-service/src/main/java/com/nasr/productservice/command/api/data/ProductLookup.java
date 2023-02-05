package com.nasr.productservice.command.api.data;

import com.nasr.core.base.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.nasr.productservice.command.api.data.ProductLookup.NAME;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {NAME})
})
@NoArgsConstructor
public class ProductLookup extends BaseEntity<String> {

    public static final String NAME="name";

    @Column(name = NAME)
    private String name;


    @Builder
    public ProductLookup(String id, String name) {
        super(id);
        this.name = name;
    }
}
