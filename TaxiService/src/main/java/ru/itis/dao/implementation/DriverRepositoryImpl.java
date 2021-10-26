package ru.itis.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dao.DriverRepository;
import ru.itis.models.Driver;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    //language=SQL
    private final String SQL_FIND_BY_ID = "select * from driver where id = ?";
    //language=SQL
    private final String SQL_FIND_ALL = "select * from driver";
    //language=SQL
    private final String SQL_FIND_SAVE = "insert into driver(first_name, last_name, age) VALUES " +
            "(?, ?, ?)";


    JdbcTemplate jdbcTemplate;

    @Autowired
    public DriverRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Driver> driverRowMapper = (row, rowNumber) ->
            Driver.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .age(row.getInt("age"))
                    .avatarId(row.getLong("avatar_id"))
                    .build();

    @Override
    public Driver findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, driverRowMapper, id);
    }

    @Override
    public Driver save(Driver item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_SAVE, new String[]{"id"});
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setInt(3, item.getAge());
            return statement;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public void delete(Long item) {

    }
}
