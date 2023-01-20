package com.nasr.eventsourcingaxon.command.api.service.validator;

import com.nasr.eventsourcingaxon.command.api.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nasr.eventsourcingaxon.command.api.service.validator.UserRegistrationValidationResult.*;

public interface UserRegistrationValidator extends Function<User, UserRegistrationValidationResult> {

    String EMAIL_PATTERN = "^(\\w|\\d)+@(gmail|yahoo).(com|ir)$";

    static UserRegistrationValidator verifyFirstName() {
        return user -> StringUtils.isBlank(user.getFirstName()) ? FIRST_NAME_NOT_VALID : OK;
    }

    static UserRegistrationValidator verifyLastName() {
        return user -> StringUtils.isBlank(user.getLastName()) ? LAST_NAME_NOT_VALID : OK;
    }

    static UserRegistrationValidator verifyEmail() {
        return user -> {
            if (StringUtils.isBlank(user.getEmail()))
                return EMAIL_NOT_VALID;

            Pattern compile = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = compile.matcher(user.getEmail());

            if (!matcher.find())
                return EMAIL_NOT_VALID;

            return OK;
        };
    }

    static UserRegistrationValidator verifyPassword() {
        return user -> StringUtils.isBlank(user.getPassword()) ? PASSWORD_NOT_VALID : OK;
    }

    default UserRegistrationValidator and(Function<User, UserRegistrationValidationResult> validationResultFunction) {
        return user -> {
            UserRegistrationValidationResult result = this.apply(user);
            return result.equals(OK) ? validationResultFunction.apply(user) : result;
        };
    }
}

