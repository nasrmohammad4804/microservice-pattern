package com.nasr.userservice.query.api.data;

import com.nasr.core.base.domain.BaseEntity;
import com.nasr.userservice.query.api.data.embeddable.UserPayment;
import lombok.*;

import javax.persistence.*;

import static com.nasr.userservice.query.api.data.User.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME,uniqueConstraints = @UniqueConstraint(columnNames = {EMAIL}))
public class User extends BaseEntity<String> {

    public static final String TABLE_NAME="user_table";
    public static final String FIRST_NAME="first_name";
    public static final String LAST_NAME="last_name";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";

    @Column(name = FIRST_NAME,nullable = false)
    private String firstName;

    @Column(name = LAST_NAME,nullable = false)
    private String lastName;

    @Column(name = EMAIL)
    private String email;

    @Column(name = PASSWORD,nullable = false)
    private String password;

    @Embedded
    private UserPayment paymentDetail;

    @Builder
    public User(String id, String firstName, String lastName, String email, String password, UserPayment paymentDetail) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.paymentDetail = paymentDetail;
    }
}
