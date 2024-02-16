package com.devshei.dabits.services.impl;

import com.devshei.dabits.domain.HabitEntity;
import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.repositories.CategoryRepository;
import com.devshei.dabits.repositories.HabitRepository;
import com.devshei.dabits.repositories.UserRepository;
import com.devshei.dabits.services.HabitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public HabitServiceImpl(HabitRepository habitRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isHabitExists(Habit habit) {
        return habitRepository.existsById(habit.getId());
    }

    @Override
    public Habit save(Habit habit) {
        final HabitEntity habitEntity = habitToHabitEntity(habit);
        final HabitEntity savedHabitEntity = habitRepository.save(habitEntity);

        return habitEntityToHabit(savedHabitEntity);
    }

    private Habit habitEntityToHabit(HabitEntity habitEntity) {
        return Habit.builder()
                .id(habitEntity.getId())
                .userId(habitEntity.getUser().getId())
                .categoryId(habitEntity.getCategory().getId())
                .habitType(habitEntity.getHabitType())
                .title(habitEntity.getTitle())
                .question(habitEntity.getQuestion())
                .description(habitEntity.getDescription())
                .frequency(habitEntity.getFrequency())
                .measurement(habitEntity.getMeasurement())
                .unit(habitEntity.getUnit())
                .createdAt(habitEntity.getCreatedAt())
                .build();
    }

    private HabitEntity habitToHabitEntity(Habit habit) {
        return HabitEntity.builder()
                .id(habit.getId())
                .user(userRepository.findById(habit.getUserId()).orElseThrow(NoSuchElementException::new))
                .category(categoryRepository.findById(habit.getCategoryId()).orElseThrow(NoSuchElementException::new))
                .habitType(habit.getHabitType())
                .title(habit.getTitle())
                .question(habit.getQuestion())
                .description(habit.getDescription())
                .frequency(habit.getFrequency())
                .measurement(habit.getMeasurement())
                .unit(habit.getUnit())
                .createdAt(habit.getCreatedAt())
                .build();
    }

    @Override
    public Optional<Habit> findById(Long id) {
        final Optional<HabitEntity> foundHabit = habitRepository.findById(id);
        return foundHabit.map(this::habitEntityToHabit);
    }

    @Override
    public List<Habit> listHabits() {
        final List<HabitEntity> foundHabits = habitRepository.findAll();
        return foundHabits.stream()
                .map(this::habitEntityToHabit)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabitById(Long id) {
        try {
            habitRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ex) {
            log.debug("Attempted to delete non-existing habit", ex);
        }
    }
}
