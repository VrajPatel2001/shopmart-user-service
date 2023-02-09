package com.shopmart.userservice.source;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private static Pattern pattern;

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
