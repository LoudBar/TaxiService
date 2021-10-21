package ru.itis.dao.base;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<K, T> {
    K save(K item);
    void delete(T item);
}
