package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Car {

    private Long id;
    private String regNumber;
    private String model;
    private Long driverId;
}
