package com.devshei.dabits.services;

import com.devshei.dabits.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isUserExistsByEmail(String email);

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> listUsers();

    void deleteUserById(Long id);

}
