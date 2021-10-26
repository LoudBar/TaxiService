package ru.itis.dao;

import ru.itis.dao.base.CrudRepository;
import ru.itis.models.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    Driver findById(Long id);
}
