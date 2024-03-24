package com.devshei.dabits.services;

import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.dto.HabitHistory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface HabitHistoryService {

    boolean doesHabitHasHistory(Habit habit);

    boolean isHabitHistoryExists(Habit habit, Date habitDate);
    HabitHistory saveHabitHistory(HabitHistory habitHistory);

    Optional<HabitHistory> findByHabitAndHabitDate(Habit habit, Date habitDate);

    Optional<HabitHistory> findByHabit(Habit habit);

    List<HabitHistory> listHabitHistories();

    void deleteHabitHistoryByHabitAndHabitDate(Habit habit, Date habitDate);

}
