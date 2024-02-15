package com.devshei.dabits;

import com.devshei.dabits.domain.UserEntity;
import com.devshei.dabits.dto.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class TestData {

    private TestData() {

    }

    public static User testUser() {
        return User.builder()
                .id(1L)
                .username("usuario1")
                .password("password1")
                .role("user")
                .createdAt(Timestamp.valueOf(LocalDateTime.of(2024, 10, 10, 9, 10, 10, 6529561)))
                .build();
    }

    public static UserEntity testUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .username("usuario1")
                .password("password1")
                .role("user")
                .createdAt(Timestamp.valueOf(LocalDateTime.of(2024, 10, 10, 9, 10, 10, 6529561)))
                .build();
    }

}
