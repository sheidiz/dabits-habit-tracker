package com.devshei.dabits.controllers;

import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.dto.HabitHistory;
import com.devshei.dabits.services.HabitHistoryService;
import com.devshei.dabits.services.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.Optional;

@Controller
public class HabitHistoryController {

    private final HabitHistoryService habitHistoryService;
    private final HabitService habitService;

    public HabitHistoryController(HabitHistoryService habitHistoryService, HabitService habitService) {
        this.habitHistoryService = habitHistoryService;
        this.habitService = habitService;
    }

    @PutMapping("/habit-history/{id}")
    public ResponseEntity<HabitHistory> createUpdateHabitHistory(
            @PathVariable final Long id,
            @RequestBody final HabitHistory habitHistory) {

        final Optional<Habit> habitOptional = habitService.findById(habitHistory.getHabitId());
        if (habitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final boolean isHabitHistoryExists = habitHistoryService.isHabitHistoryExists(habitOptional.get(), habitHistory.getHabitDate());

        habitHistory.setHabitId(id);

        final HabitHistory savedHabitHistory = habitHistoryService.saveHabitHistory(habitHistory);

        if (isHabitHistoryExists) {
            return new ResponseEntity<>(savedHabitHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedHabitHistory, HttpStatus.CREATED);
        }
    }
    @GetMapping(path = "/habit-history/{habitId}")
    public ResponseEntity<HabitHistory> getHabitHistory(
            @PathVariable final Long habitId) {

        final Optional<Habit> foundHabit = habitService.findById(habitId);
        if (foundHabit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final Optional<HabitHistory> foundHabitHistory = habitHistoryService.findByHabit(foundHabit.get());
        return foundHabitHistory.map(habitHistory -> new ResponseEntity<>(habitHistory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/habit-history/{habitId}/date/{habitDate}")
    public ResponseEntity<HabitHistory> getHabitHistory(
            @PathVariable final Long habitId,
            @PathVariable final Date habitDate) {

        final Optional<Habit> habitOptional = habitService.findById(habitId);
        if (habitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final Optional<HabitHistory> foundHabitHistory = habitHistoryService.findByHabitAndHabitDate(habitOptional.get(), habitDate);
        return foundHabitHistory.map(habitHistory -> new ResponseEntity<>(habitHistory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
