package com.devshei.dabits.controllers;

import com.devshei.dabits.dto.User;
import com.devshei.dabits.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<User> createUpdateUser(
            @PathVariable final Long id,
            @RequestBody final User user) {
        user.setId(id);

        final boolean isUserExists = userService.isUserExists(user);
        final User savedUser = userService.save(user);

        if (isUserExists) {
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable final Long id) {
        final Optional<User> foundUser = userService.findById(id);
        return foundUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable final Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
