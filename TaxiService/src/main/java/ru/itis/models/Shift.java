package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
@Builder
public class Shift {

    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Driver driver;
    private Long customerId;
    private Timestamp date;
}
