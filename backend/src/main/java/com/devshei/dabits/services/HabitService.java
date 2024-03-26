package com.devshei.dabits.services;

import com.devshei.dabits.dto.Category;
import com.devshei.dabits.dto.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitService {
    boolean isHabitExists(Habit habit);

    Habit save(Habit habit);

    Optional<Habit> findById(Long id);

    List<Habit> listHabits();

    void deleteHabitById(Long id);
}
