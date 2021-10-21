package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.dto.ShiftDto;
import ru.itis.dto.ShiftForm;


@Service
public interface ShiftService {
    void takeTrip(Long id, ShiftForm shiftForm);
}
