package ru.itis.dao.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dao.ShiftRepository;
import ru.itis.models.Driver;
import ru.itis.models.Shift;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ShiftRepositoryImpl implements ShiftRepository {

    //language=SQL
    private final String SQL_SAVE = "insert into shift(customer_id, driver_id, departure_place, arrival_place, date)" +
            "VALUES (?, ?, ?, ?, ?)";
    //language=SQL
    private final String SQL_FIND_ALL = "select shift.id as shift_id, customer_id, driver_id, departure_place, arrival_place, date, " +
                        "driver.id as driver_id,  first_name, last_name, age, avatar_id from shift left join driver on shift.driver_id = " +
                        "driver.id where customer_id = ?";
    //language=SQl
    private final String SQL_FIND_BY_ID = "select shift.id as shift_id, customer_id, driver_id, departure_place, arrival_place, date, " +
            "driver.id as driver_id,  first_name, last_name, age, avatar_id from shift left join driver on shift.driver_id = " +
            "driver.id where shift.id = ?";

    JdbcTemplate jdbcTemplate;

    public ShiftRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Shift> shiftRowMapper = (row, rowNumber) ->
            Shift.builder()
                    .id(row.getLong("shift_id"))
                    .customerId(row.getLong("customer_id"))
                    .driver(Driver.builder()
                            .id(row.getLong("driver_id"))
                            .firstName(row.getString("first_name"))
                            .lastName(row.getString("last_name"))
                            .age(row.getInt("age"))
                            .avatarId(row.getLong("avatar_id"))
                            .build())
                    .departurePlace(row.getString("departure_place"))
                    .arrivalPlace(row.getString("arrival_place"))
                    .date(row.getTimestamp("date"))
                    .build();

    @Override
    public Shift save(Shift item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, new String[]{"id"});
            statement.setLong(1, item.getCustomerId());
            statement.setLong(2, item.getDriver().getId());
            statement.setString(3, item.getDeparturePlace());
            statement.setString(4, item.getArrivalPlace());
            statement.setTimestamp(5, date);
            return statement;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        item.setDate(date);
        return item;
    }

    @Override
    public void delete(Long item) {

    }

    @Override
    public List<Shift> findAll(Long id) {
        return jdbcTemplate.query(SQL_FIND_ALL, shiftRowMapper, id);
    }

    @Override
    public Optional<Shift> findById(Long shiftId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, shiftRowMapper, shiftId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
