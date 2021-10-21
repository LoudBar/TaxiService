package ru.itis.dao;

import org.springframework.stereotype.Repository;
import ru.itis.dao.base.CrudRepository;
import ru.itis.models.Shift;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {
    List<Shift> findAll(Long id);
}
