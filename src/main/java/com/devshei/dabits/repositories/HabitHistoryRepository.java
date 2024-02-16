package com.devshei.dabits.repositories;

import com.devshei.dabits.domain.HabitHistoryEntity;
import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.dto.HabitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface HabitHistoryRepository extends JpaRepository<HabitHistoryEntity, Long> {
    boolean existsByHabitAndHabitDate(Habit habit, Date habitDate);

    Optional<HabitHistoryEntity> findByHabitAndHabitDate(Habit habit, Date habitDate);

    void deleteByHabitAndDate(Habit habit, Date habitDate);
}
