package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.dto.ShiftDto;
import ru.itis.dto.ShiftForm;
import ru.itis.models.Shift;


@Service
public interface ShiftService {
    Shift takeTrip(Long id, ShiftForm shiftForm);
}
