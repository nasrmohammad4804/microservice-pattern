package com.nasr.userservice.query.api.service;

import com.nasr.core.model.UserPaymentDetailResponseDto;
import com.nasr.userservice.query.api.data.User;

import java.util.List;

public interface UserQueryService {


    UserPaymentDetailResponseDto getUserPaymentDetail(String userId);

    void saveAll(List<User> user);

    long countAll();
}
