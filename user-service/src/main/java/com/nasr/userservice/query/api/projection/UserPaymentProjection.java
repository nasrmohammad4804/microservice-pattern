package com.nasr.userservice.query.api.projection;

import com.nasr.core.model.UserPaymentDetail;
import com.nasr.core.query.GetUserPaymentDetailQuery;
import com.nasr.userservice.query.api.service.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserPaymentProjection {

    @Autowired
    private UserQueryService userQueryService;

    @QueryHandler
    public UserPaymentDetail handle(GetUserPaymentDetailQuery query){
        log.info("GetCustomerPaymentDetailQuery for userId : {} is received",query.getUserId());

        return userQueryService.getUserPaymentDetail(query.getUserId());

    }
}
