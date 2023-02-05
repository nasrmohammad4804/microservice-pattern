package com.nasr.userservice.query.api.repository;

import com.nasr.userservice.query.api.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<User,String> {
}
