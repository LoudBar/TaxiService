package ru.itis.services;

import org.springframework.stereotype.Repository;
import ru.itis.dto.CustomerDto;
import ru.itis.dto.CustomerForm;


@Repository
public interface SignInService {
    CustomerDto signIn(CustomerForm form);
}
