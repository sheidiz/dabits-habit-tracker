package com.devshei.dabits.controllers;

import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.services.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PutMapping(path = "/habits/{id}")
    public ResponseEntity<Habit> createUpdateHabit(
            @PathVariable final Long id,
            @RequestBody final Habit habit) {
        habit.setId(id);

        final boolean isHabitExists = habitService.isHabitExists(habit);
        final Habit savedHabit = habitService.save(habit);

        if (isHabitExists) {
            return new ResponseEntity<>(savedHabit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedHabit, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/habits/{id}")
    public ResponseEntity<Habit> getHabit(@PathVariable final Long id) {
        final Optional<Habit> foundHabit = habitService.findById(id);
        return foundHabit.map(habit -> new ResponseEntity<>(habit, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/habits")
    public ResponseEntity<List<Habit>> listHabits() {
        return new ResponseEntity<>(habitService.listHabits(), HttpStatus.OK);
    }

    @DeleteMapping(path="/habits/{id}")
    public ResponseEntity deleteHabit(@PathVariable final Long id){
        habitService.deleteHabitById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
