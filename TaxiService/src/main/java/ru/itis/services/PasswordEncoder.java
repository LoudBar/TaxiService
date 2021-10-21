package ru.itis.services;

import org.springframework.stereotype.Repository;

@Repository
public interface PasswordEncoder {
    boolean matches(String password, String hashPassword);
    String encode(String password);
}
