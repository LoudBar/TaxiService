package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class Shift {

    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Long driverId;
    private Long customerId;
    private Date date;
}
