package com.epam.mentoring.springmvc.passwordencoder;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Kaikenov Adilhan
**/
public class DisabledPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(final CharSequence password) {
        return password.toString();
    }

    @Override
    public boolean matches(final CharSequence password, final String encodedPassword) {
        return encodedPassword.equals(password.toString());
    }
}
