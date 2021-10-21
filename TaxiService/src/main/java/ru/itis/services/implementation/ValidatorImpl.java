package ru.itis.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.dao.CustomerRepository;
import ru.itis.dto.CustomerForm;
import ru.itis.services.validation.ErrorEntity;
import ru.itis.services.validation.Validator;

import java.util.Optional;

@Repository
public class ValidatorImpl implements Validator {

    private final CustomerRepository customerRepository;
    private final String phoneRegex = "^((\\+7|7|8)+([0-9]){10})$";

    @Autowired
    public ValidatorImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<ErrorEntity> validate(CustomerForm form) {
        if(!form.getPhoneNumber().matches(phoneRegex)) {
            return Optional.of(ErrorEntity.INVALID_PHONE_NUMBER);
        } else if(customerRepository.findByPhoneNumber(form.getPhoneNumber()).isPresent()) {
            return Optional.of(ErrorEntity.PHONE_NUMBER_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}