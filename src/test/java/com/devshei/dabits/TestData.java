package com.devshei.dabits;

import com.devshei.dabits.domain.CategoryEntity;
import com.devshei.dabits.domain.HabitEntity;
import com.devshei.dabits.domain.HabitHistoryEntity;
import com.devshei.dabits.domain.UserEntity;
import com.devshei.dabits.dto.Category;
import com.devshei.dabits.dto.Habit;
import com.devshei.dabits.dto.HabitHistory;
import com.devshei.dabits.dto.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class TestData {

    private TestData() {}
    private static LocalDateTime exampleDateTime = LocalDateTime.of(2024, 10, 10, 9, 10, 10, 6529561);

    public static User testUser() {
        return User.builder()
                .id(1L)
                .username("user 1")
                .password("password 1")
                .role("user")
                .createdAt(Timestamp.valueOf(exampleDateTime))
                .build();
    }

    public static UserEntity testUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .username("JohnSmith")
                .password("password123")
                .role("user")
                .createdAt(Timestamp.valueOf(exampleDateTime))
                .build();
    }
    public static Category testCategory(){
        return Category.builder()
                .id(1L)
                .title("Health")
                .color("#00ff00")
                .build();
    }

    public static CategoryEntity testCategoryEntity(){
        return CategoryEntity.builder()
                .id(1L)
                .title("Health")
                .color("#00ff00")
                .build();
    }

    public static Habit testHabit(){
        return Habit.builder()
                .id(1L)
                .userId(testUser().getId())
                .categoryId(testCategory().getId())
                .habitType("Up")
                .title("Drink water")
                .question("How much water did you drink today?")
                .description("Hidratate!")
                .frequency("3")
                .measurement("3")
                .unit("glass")
                .createdAt(Timestamp.valueOf(exampleDateTime))
                .build();
    }

    public static HabitEntity testHabitEntity(){
        return HabitEntity.builder()
                .id(1L)
                .user(testUserEntity())
                .category(testCategoryEntity())
                .habitType("Up")
                .title("Drink water")
                .question("How much water did you drink today?")
                .description("Hidratate!")
                .frequency("3")
                .measurement("3")
                .unit("glass")
                .createdAt(Timestamp.valueOf(exampleDateTime))
                .build();
    }

    public static HabitHistory testHabitHistory(){
        return HabitHistory.builder()
                .habitId(testHabit().getId())
                .habitDate(Date.valueOf(String.valueOf(exampleDateTime)))
                .numericValue(2)
                .build();
    }

    public static HabitHistoryEntity testHabitHistoryEntity(){
        return HabitHistoryEntity.builder()
                .habit(testHabitEntity())
                .habitDate(Date.valueOf(String.valueOf(exampleDateTime)))
                .numericValue(2)
                .build();
    }

}
