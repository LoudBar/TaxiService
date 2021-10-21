package ru.itis.services.validation;

import ru.itis.dto.CustomerForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validate(CustomerForm form);
}