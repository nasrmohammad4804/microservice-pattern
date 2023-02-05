package com.nasr.userservice.query.api.init;

import com.nasr.core.model.UserPaymentDetailResponseDto;
import com.nasr.userservice.query.api.data.User;
import com.nasr.userservice.query.api.data.embeddable.UserPayment;
import com.nasr.userservice.query.api.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private UserQueryService userQueryService;

    @PostConstruct
    public void init(){

        if (isNotExistsUser())
            createDefaultUser();
    }

    private boolean isNotExistsUser() {
        return userQueryService.countAll() == 0;
    }

    private void createDefaultUser() {

        User user1 = User.builder().id("4804").firstName("mohammad").lastName("nasr")
                .email("nasrmohammad4804@gmail.com").password("12345")
                .paymentDetail(new UserPayment("5041721098731906", "437", LocalDate.of(2025, 6, 13)))
                .build();


        User user2 = User.builder().id("4805").firstName("aida").lastName("fallah")
                .email("aida@gmail.com").password("6789")
                .paymentDetail(new UserPayment("6037997543190711", "667", LocalDate.of(2027,3,8)))
                .build();

        User user3 = User.builder().id("4806").firstName("javad").lastName("zare")
                .email("javad@gmail.com").password("2222")
                .paymentDetail(new UserPayment("5022679132380914", "138", LocalDate.of(2024,11,24)))
                .build();

        List<User> users = List.of(user1,user2,user3);

        userQueryService.saveAll(users);
    }
}
