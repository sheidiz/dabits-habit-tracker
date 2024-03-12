package com.devshei.dabits.services.impl;

import com.devshei.dabits.domain.HabitEntity;
import com.devshei.dabits.domain.HabitHistoryEntity;
import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.dto.HabitHistory;
import com.devshei.dabits.repositories.HabitHistoryRepository;
import com.devshei.dabits.repositories.HabitRepository;
import com.devshei.dabits.services.HabitHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HabitHistoryServiceImpl implements HabitHistoryService {

    final HabitHistoryRepository habitHistoryRepository;
    final HabitRepository habitRepository;

    @Autowired
    public HabitHistoryServiceImpl(HabitHistoryRepository habitHistoryRepository, HabitRepository habitRepository) {
        this.habitHistoryRepository = habitHistoryRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public boolean doesHabitHasHistory(Habit habit) {
        return habitHistoryRepository.existsByHabit(habit);
    }

    @Override
    public boolean isHabitHistoryExists(Habit habit, Date habitDate) {
        return habitHistoryRepository.existsByHabitAndHabitDate(habit, habitDate);
    }

    @Override
    public HabitHistory saveHabitHistory(HabitHistory habitHistory) {
        final HabitHistoryEntity habitHistoryEntity = habitHistoryToHabitHistoryEntity(habitHistory);
        final HabitHistoryEntity savedHabitHistoryEntity = habitHistoryRepository.save(habitHistoryEntity);

        return habitHistoryEntityToHabitHistory(savedHabitHistoryEntity);
    }

    private HabitHistoryEntity habitHistoryToHabitHistoryEntity(HabitHistory habitHistory) {
        return HabitHistoryEntity.builder()
                .habit(habitRepository.findById(habitHistory.getHabitId()).orElseThrow(NoSuchElementException::new))
                .habitDate(habitHistory.getHabitDate())
                .numericValue(habitHistory.getNumericValue())
                .build();
    }

    private HabitHistory habitHistoryEntityToHabitHistory(HabitHistoryEntity habitHistoryEntity) {
        return HabitHistory.builder()
                .habitId(habitHistoryEntity.getHabit().getId())
                .habitDate(habitHistoryEntity.getHabitDate())
                .numericValue(habitHistoryEntity.getNumericValue())
                .build();
    }

    @Override
    public Optional<HabitHistory> findByHabitAndDate(Habit habit, Date habitDate) {
        final Optional<HabitHistoryEntity> foundHabitHistory = habitHistoryRepository.findByHabitAndHabitDate(habit, habitDate);

        return foundHabitHistory.map(this::habitHistoryEntityToHabitHistory);
    }

    @Override
    public Optional<HabitHistory> findByHabit(Habit habit) {
        final Optional<HabitHistoryEntity> foundHabitHistory = habitHistoryRepository.findByHabit(habit);

        return foundHabitHistory.map(this::habitHistoryEntityToHabitHistory);
    }

    @Override
    public List<HabitHistory> listHabitHistories() {
        final List<HabitHistoryEntity> foundHabitHistories = habitHistoryRepository.findAll();

        return foundHabitHistories.stream()
                .map(this::habitHistoryEntityToHabitHistory)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabitHistoryByHabitAndDate(Habit habit, Date habitDate) {
        try {
            habitHistoryRepository.deleteByHabitAndDate(habit, habitDate);
        } catch (final EmptyResultDataAccessException ex) {
            log.debug("Attempted to delete non-existing habit history", ex);
        }
    }
}
