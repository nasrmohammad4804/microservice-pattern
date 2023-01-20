package com.nasr.eventsourcingaxon.command.api.aggregates;

import com.nasr.eventsourcingaxon.command.api.base.aggregate.BaseAggregate;
import com.nasr.eventsourcingaxon.command.api.commands.CreateUserCommand;
import com.nasr.eventsourcingaxon.command.api.commands.UpdateUserCommand;
import com.nasr.eventsourcingaxon.command.api.event.userevent.UserCreatedEvent;
import com.nasr.eventsourcingaxon.command.api.event.userevent.UserUpdatedEvent;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
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

    @CommandHandler
    public void on(UpdateUserCommand command){

        apply(
                UserUpdatedEvent.builder()
                        .id(command.getId())
                        .firstName(command.getFirstName())
                        .lastName(command.getLastName())
                        .password(command.getPassword())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event){
        this.id=event.getId();
        this.firstName=event.getFirstName();
        this.lastName=event.getLastName();
        this.email=event.getEmail();
        this.password=event.getPassword();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event){

        this.id=event.getId();

        if (StringUtils.isNotBlank(event.getFirstName()))
            this.firstName=event.getFirstName();

        if (StringUtils.isNotBlank(event.getLastName()))
            this.lastName=event.getLastName();

        if (StringUtils.isNotBlank(event.getPassword()))
            this.password=event.getPassword();
    }
}
