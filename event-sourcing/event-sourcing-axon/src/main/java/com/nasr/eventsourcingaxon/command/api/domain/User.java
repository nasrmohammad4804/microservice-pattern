package com.nasr.eventsourcingaxon.command.api.domain;

import com.nasr.eventsourcingaxon.command.api.base.domain.BaseEntity;
import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(name = "email_constraint",columnNames = User.EMAIL)
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<String> {

    public static final String EMAIL= "email";


    private String firstName;

    private String lastName;

    @Column(name = EMAIL)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new LinkedHashSet<>();

    public User(String id) {
        super(id);
    }
}
