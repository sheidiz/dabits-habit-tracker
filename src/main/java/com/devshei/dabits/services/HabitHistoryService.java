package com.devshei.dabits.services;

import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.dto.HabitHistory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface HabitHistoryService {
    boolean isHabitHistoryExists(Habit habit, Date habitDate);
    HabitHistory saveHabitHistory(HabitHistory habitHistory);

    Optional<HabitHistory> findByHabitAndDate(Habit habit, Date habitDate);

    List<HabitHistory> listHabitHistories();

    void deleteHabitHistoryByHabitAndDate(Habit habit, Date habitDate);

}
