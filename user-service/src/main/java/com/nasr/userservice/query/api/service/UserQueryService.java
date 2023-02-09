package com.nasr.userservice.query.api.service;


import com.nasr.core.model.UserPaymentDetail;
import com.nasr.userservice.query.api.data.User;

import java.util.List;

public interface UserQueryService {

    UserPaymentDetail getUserPaymentDetail(String userId);

    void saveAll(List<User> user);

    long countAll();
}
