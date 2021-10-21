package ru.itis.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dao.CustomerRepository;
import ru.itis.models.Customer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.*;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    //language=SQl
    private final String SQL_SAVE = "insert into customer(first_name, last_name, age, phone_number, password,  avatar_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    //language=SQL
    private final String SQL_UPDATE = "update customer set first_name = ?, last_name = ?, " +
            "phone_number = ?, password = ?, age = ? where id = ?";
    //language=SQL
    private final String SQL_FIND_BY_ID = "select * from customer where id = ?";
    //language=SQL
    private final static String SQL_PUT_AVATAR = "update customer set avatar_id = ? where id = ?";
    //language=SQL
    private final static String SQL_FIND_BY_PHONE_NUMBER = "select * from customer where " +
            "phone_number = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Customer> customerRowMapper = (row, rowNumber) ->
            Customer.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .phoneNumber(row.getString("phone_number"))
                    .password(row.getString("password"))
                    .age(row.getInt("age"))
                    .avatarId(row.getLong("avatar_id"))
                    .build();

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_SAVE, new String[]{"id"});
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setInt(3, customer.getAge());
                statement.setString(4, customer.getPhoneNumber());
                statement.setString(5, customer.getPassword());
                if(customer.getAvatarId() != null) {
                    statement.setLong(6, customer.getAvatarId());
                } else {
                    statement.setNull(6, Types.NULL);
                }
                return statement;
            }, keyHolder);
            customer.setId(keyHolder.getKey().longValue());
        } else
        {
            jdbcTemplate.update(SQL_UPDATE,
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAge(),
                    customer.getPhoneNumber(),
                    customer.getPassword(),
                    customer.getAvatarId(),
                    customer.getId());
        }
        return customer;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Customer> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, customerRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void putAvatar(Long userId, Long fileId) {
        jdbcTemplate.update(SQL_PUT_AVATAR, fileId, userId);
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String number) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_PHONE_NUMBER, customerRowMapper, number));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
