package com.devshei.dabits.controllers;

import com.devshei.dabits.domain.UserEntity;
import com.devshei.dabits.dto.CreateUser;
import com.devshei.dabits.dto.User;
import com.devshei.dabits.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody final CreateUser createUser) {

        if (userService.isUserExistsByEmail(createUser.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        final User user = createUserToUser(createUser);

        final User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    private User createUserToUser(CreateUser user) {
        return User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    private UserEntity createUserToUserEntity(CreateUser user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable final Long id) {
        final Optional<User> foundUser = userService.findById(id);
        return foundUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(
            @RequestBody final CreateUser createUser) {

        final Optional<User> existingUser = userService.findByEmail(createUser.getEmail());

        if (existingUser.isPresent()) {
            existingUser.get().setName(createUser.getName());
            existingUser.get().setPassword(createUser.getPassword());
            existingUser.get().setRole(createUser.getRole());
            final User updatedUser = userService.save(existingUser.get());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable final Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
