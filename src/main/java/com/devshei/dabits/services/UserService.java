package com.devshei.dabits.services;

import com.devshei.dabits.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isUserExists(User user);

    User save(User user);

    Optional<User> findById(Long id);

    List<User> listUsers();

    void deleteUseryById(Long id);
}
