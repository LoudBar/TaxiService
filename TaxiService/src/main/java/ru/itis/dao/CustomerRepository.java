package ru.itis.dao;

import org.springframework.stereotype.Repository;
import ru.itis.dao.base.CrudRepository;
import ru.itis.models.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    void putAvatar(Long userId, Long fileId);
    Optional<Customer> findByPhoneNumber(String number);
}
