package com.nasr.eventsourcingaxon.command.api.domain;

import com.nasr.eventsourcingaxon.command.api.enumerate.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    public static final String USER_ID="user_id";

    @Id
    private String id;

    private Double balance;

    private AccountStatus status;

    private String currency;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;
}
