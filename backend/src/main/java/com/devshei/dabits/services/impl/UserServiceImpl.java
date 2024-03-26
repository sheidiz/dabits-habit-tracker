package com.devshei.dabits.services.impl;

import com.devshei.dabits.domain.UserEntity;
import com.devshei.dabits.dto.User;
import com.devshei.dabits.repositories.UserRepository;
import com.devshei.dabits.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(final User user) {
        final UserEntity userEntity = userToUserEntity(user);
        final UserEntity savedUserEntity = userRepository.save(userEntity);

        return userEntityToUser(savedUserEntity);
    }

    private UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    private User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

    @Override
    public Optional<User> findById(Long id) {
        final Optional<UserEntity> foundUser = userRepository.findById(id);
        return foundUser.map(this::userEntityToUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final Optional<UserEntity> foundUser = userRepository.findByEmail(email);
        return foundUser.map(this::userEntityToUser);
    }

    @Override
    public List<User> listUsers() {
        final List<UserEntity> foundUsers = userRepository.findAll();
        return foundUsers.stream()
                .map(this::userEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ex) {
            log.debug("Attempted to delete non-existing user", ex);
        }
    }
}
