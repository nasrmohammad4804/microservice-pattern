package com.nasr.eventsourcingaxon.command.api.aggregates;

import com.nasr.eventsourcingaxon.command.api.base.aggregate.BaseAggregate;
import com.nasr.eventsourcingaxon.command.api.commands.CreateAccountCommand;
import com.nasr.eventsourcingaxon.command.api.commands.CreditMoneyAccountCommand;
import com.nasr.eventsourcingaxon.command.api.commands.DebitMoneyAccountCommand;
import com.nasr.eventsourcingaxon.command.api.enumerate.AccountStatus;
import com.nasr.eventsourcingaxon.command.api.event.accountevent.AccountActivatedEvent;
import com.nasr.eventsourcingaxon.command.api.event.accountevent.AccountCreatedEvent;
import com.nasr.eventsourcingaxon.command.api.event.accountevent.MoneyCreditedEvent;
import com.nasr.eventsourcingaxon.command.api.event.accountevent.MoneyDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AccountAggregate extends BaseAggregate<String> {

    private Double balance;

    private String currency;

    private AccountStatus status;

    private String userId;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        //we validate data on service and after that in service layer raise command with command gateway
        apply(
                AccountCreatedEvent.builder()
                        .id(command.getId())
                        .balance(command.getBalance())
                        .currency(command.getCurrency())
                        .userId(command.getUserId())
                        .build()

        );
    }

    public AccountAggregate() {
    }

    @CommandHandler
    public void on(CreditMoneyAccountCommand command){
        apply(
                MoneyCreditedEvent.builder()
                        .id(command.getId())
                        .amount(command.getAmount())
                        .currency(command.getCurrency())
                        .build()
        );
    }

    @CommandHandler
    public void on(DebitMoneyAccountCommand command){
        apply(
                MoneyDebitedEvent.builder()
                        .amount(command.getAmount())
                        .id(command.getId())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.id=event.getId();
        this.balance=event.getBalance();
        this.currency=event.getCurrency();
        this.userId=event.getUserId();
        this.status=event.getStatus();

        apply(
                AccountActivatedEvent.builder()
                        .id(event.getId())
                        .status(AccountStatus.ACTIVE)
        );
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.id=event.getId();
        this.status=event.getStatus();
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent event){
        this.id=event.getId();
        this.balance+=event.getAmount();
    }

    public void on(MoneyDebitedEvent event){
        this.id=event.getId();
        this.balance-=event.getAmount();
    }
}
