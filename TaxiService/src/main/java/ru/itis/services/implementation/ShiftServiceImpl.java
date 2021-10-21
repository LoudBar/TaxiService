package ru.itis.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.ShiftRepository;
import ru.itis.dto.ShiftForm;
import ru.itis.models.Shift;
import ru.itis.services.ShiftService;

import java.util.Date;


@Service
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public void takeTrip(Long id, ShiftForm shiftForm) {

        Shift shift = Shift.builder()
                .customerId(id)
                .driverId(1L)
                .departurePlace(shiftForm.getDeparturePlace())
                .arrivalPlace(shiftForm.getArrivalPlace())
                .build();

        shiftRepository.save(shift);
    }
}
