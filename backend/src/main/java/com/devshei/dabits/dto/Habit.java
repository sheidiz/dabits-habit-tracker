package com.devshei.dabits.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habit {

    private Long id;
    private Long userId;
    private Long categoryId;
    private String habitType;
    private String title;
    private String question;
    private String description;
    private String frequency;
    private String measurement;
    private String unit;
    private Timestamp createdAt;
}
