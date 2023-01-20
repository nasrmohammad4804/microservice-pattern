package com.nasr.eventsourcingaxon.command.api.repository;

import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);

    @Modifying
    @Query("update User u set u.firstName=case when :#{#dto.firstName} is null   then u.firstName else :#{#dto.firstName} end ," +
            "u.lastName=case when :#{#dto.lastName} is null then u.lastName else :#{#dto.lastName} end , " +
            "u.password=case when :#{#dto.password} is null then u.password else :#{#dto.password} end where u.id=:id")
    void  update(String id, UpdateUserRequestDto dto);
}
