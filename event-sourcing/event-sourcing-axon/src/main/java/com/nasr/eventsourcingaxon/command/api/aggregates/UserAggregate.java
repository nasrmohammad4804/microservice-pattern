package com.nasr.eventsourcingaxon.command.api.aggregates;

import com.nasr.eventsourcingaxon.command.api.base.aggregate.BaseAggregate;
import com.nasr.eventsourcingaxon.command.api.commands.CreateUserCommand;
import com.nasr.eventsourcingaxon.command.api.event.userevent.UserCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Setter
@Getter
public class UserAggregate extends BaseAggregate<String> {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand command){
        apply(
                UserCreatedEvent.builder()
                        .id(command.getId())
                        .firstName(command.getFirstName())
                        .lastName(command.getLastName())
                        .email(command.getEmail())
                        .password(command.getPassword())
                        .build()
        );
    }

    //todo - add handler for updateUserCommand
    // and after that put eventSourcingHandler

    @EventSourcingHandler
    public void on(UserCreatedEvent event){
        this.id=event.getId();
        this.firstName=event.getFirstName();
        this.lastName=event.getLastName();
        this.email=event.getEmail();
        this.password=event.getPassword();
    }
}
