package com.nasr.eventsourcingaxon.command.api.repository;

import com.nasr.eventsourcingaxon.command.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
