package ru.itis.services.implementation;

import org.springframework.stereotype.Repository;
import ru.itis.services.PasswordEncoder;

@Repository
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public boolean matches(String password, String hashPassword) {
        return password.equals(hashPassword);
    }

    @Override
    public String encode(String password) {
        return password;
    }
}