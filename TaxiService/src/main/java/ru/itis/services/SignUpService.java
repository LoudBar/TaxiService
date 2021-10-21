package ru.itis.services;

import org.springframework.stereotype.Repository;
import ru.itis.dto.CustomerForm;

@Repository
public interface SignUpService {
    void signUp(CustomerForm form);
}
