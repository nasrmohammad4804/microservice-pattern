package com.nasr.eventsourcingaxon.command.api.repository;

import com.nasr.eventsourcingaxon.command.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);
}
