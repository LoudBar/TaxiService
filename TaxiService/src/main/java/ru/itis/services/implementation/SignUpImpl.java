package ru.itis.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.dao.CustomerRepository;
import ru.itis.dto.CustomerForm;
import ru.itis.exceptions.ValidationException;
import ru.itis.models.Customer;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.SignUpService;
import ru.itis.services.validation.ErrorEntity;
import ru.itis.services.validation.Validator;

import java.util.Optional;

public class SignUpImpl implements SignUpService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(CustomerForm form) {
        Optional<ErrorEntity> optionalError = validator.validate(form);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        Customer customer = Customer.builder()
                .phoneNumber(form.getPhoneNumber())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .age(form.getAge())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        customerRepository.save(customer);
    }
}