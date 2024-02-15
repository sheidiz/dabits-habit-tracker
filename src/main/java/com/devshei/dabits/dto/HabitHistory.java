package com.devshei.dabits.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class HabitHistory {
    private Long userId;
    private Long habitId;
    private Date habitDate;
    private int numericValue;
}
