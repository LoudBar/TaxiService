package ru.itis.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.itis.dao.CustomerRepository;
import ru.itis.dto.CustomerDto;
import ru.itis.dto.CustomerForm;
import ru.itis.exceptions.ValidationException;
import ru.itis.models.Customer;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.SignInService;
import ru.itis.services.validation.ErrorEntity;

@Service
public class SignInServiceImpl implements SignInService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignInServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDto signIn(CustomerForm form) {
        Customer customer = customerRepository.findByPhoneNumber(form.getPhoneNumber())
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        if (!passwordEncoder.matches(form.getPassword(), customer.getPassword())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
        return CustomerDto.form(customer);
    }
}
