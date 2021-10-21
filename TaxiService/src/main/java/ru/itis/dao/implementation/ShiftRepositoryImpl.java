package ru.itis.dao.implementation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dao.ShiftRepository;
import ru.itis.models.Shift;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ShiftRepositoryImpl implements ShiftRepository {

    //language=SQL
    private final String SQL_SAVE = "insert into shift(customer_id, driver_id, departure_place, arrival_place, date)" +
            "VALUES (?, ?, ?, ?, ?)";
    //language=SQL
    private final String SQL_FIND_ALL = "select * from shift where customer_id = ?";

    JdbcTemplate jdbcTemplate;

    public ShiftRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Shift> shiftRowMapper = (row, rowNumber) ->
            Shift.builder()
                    .id(row.getLong("id"))
                    .customerId(row.getLong("customer_id"))
                    .driverId(row.getLong("driver_id"))
                    .departurePlace(row.getString("departure_place"))
                    .arrivalPlace(row.getString("arrival_place"))
                    .date(row.getDate("date"))
                    .build();

    @Override
    public Shift save(Shift item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, new String[]{"id"});
            statement.setLong(1, item.getCustomerId());
            statement.setLong(2, 1L);
            statement.setString(3, item.getDeparturePlace());
            statement.setString(4, item.getArrivalPlace());
            statement.setDate(5, (Date) item.getDate());
            return statement;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public void delete(Long item) {

    }

    @Override
    public List<Shift> findAll(Long id) {
        return jdbcTemplate.query(SQL_FIND_ALL, shiftRowMapper, id);
    }
}
