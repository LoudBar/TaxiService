package ru.itis.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.DriverRepository;
import ru.itis.dao.ShiftRepository;
import ru.itis.dto.ShiftForm;
import ru.itis.models.Driver;
import ru.itis.models.Shift;
import ru.itis.services.ShiftService;

import java.util.Date;

public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;
    private final DriverRepository driverRepository;

    public ShiftServiceImpl(ShiftRepository shiftRepository, DriverRepository driverRepository) {
        this.shiftRepository = shiftRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Shift takeTrip(Long id, ShiftForm shiftForm) {

        Shift shift = Shift.builder()
                .customerId(id)
                .driver(driverRepository.findById(rnd(5)))
                .departurePlace(shiftForm.getDeparturePlace())
                .arrivalPlace(shiftForm.getArrivalPlace())
                .build();

        shiftRepository.save(shift);

        return shift;
    }

    public Long rnd(int max)
    {
        Double ans = Math.random() * ++max;
        return ans.longValue();
    }
}
