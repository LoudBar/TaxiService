package ru.itis.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.Shift;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ShiftDto {

    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Long driverId;
    private Long customerId;
    private Date date;

    public static ShiftDto form(Shift shift) {
        return ShiftDto.builder()
                .id(shift.getId())
                .departurePlace(shift.getDeparturePlace())
                .arrivalPlace(shift.getArrivalPlace())
                .driverId(shift.getDriverId())
                .customerId(shift.getCustomerId())
                .date(shift.getDate())
                .build();
    }
}
