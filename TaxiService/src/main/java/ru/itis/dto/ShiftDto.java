package ru.itis.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.Driver;
import ru.itis.models.Shift;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ShiftDto {

    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Driver driver;
    private Long customerId;
    private Timestamp date;

    public static ShiftDto form(Shift shift) {
        return ShiftDto.builder()
                .id(shift.getId())
                .departurePlace(shift.getDeparturePlace())
                .arrivalPlace(shift.getArrivalPlace())
                .driver(shift.getDriver())
                .customerId(shift.getCustomerId())
                .date(shift.getDate())
                .build();
    }
}
