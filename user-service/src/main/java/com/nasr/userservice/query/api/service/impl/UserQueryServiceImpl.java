package com.nasr.userservice.query.api.service.impl;

import com.nasr.core.model.UserPaymentDetail;
import com.nasr.userservice.core.exception.UserNotFoundException;
import com.nasr.userservice.query.api.data.User;
import com.nasr.userservice.query.api.repository.UserQueryRepository;
import com.nasr.userservice.query.api.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nasr.core.model.UserPaymentDetail.PaymentDetail.builder;

@Service
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserQueryRepository repository;

    @Override
    public UserPaymentDetail getUserPaymentDetail(String userId) {

        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("dont find any user with id: " + userId));

        return UserPaymentDetail.builder()
                .customerId(userId)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .paymentDetail(
                        builder()
                                .cardNumber(user.getPaymentDetail().getCardNumber())
                                .cvv2(user.getPaymentDetail().getCvv2())
                                .expirationDate(user.getPaymentDetail().getExpirationDate())
                                .build()
                )
                .build();
    }

    @Override
    @Transactional
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    public long countAll() {
        return repository.count();
    }
}
