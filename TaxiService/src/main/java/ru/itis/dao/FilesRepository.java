package ru.itis.dao;

import org.springframework.stereotype.Repository;
import ru.itis.dao.base.CrudRepository;
import ru.itis.models.FileInfo;

import java.util.Optional;


@Repository
public interface FilesRepository extends CrudRepository<FileInfo, Long> {
    Optional<FileInfo> findById(Long id);
}
