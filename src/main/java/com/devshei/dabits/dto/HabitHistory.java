package com.devshei.dabits.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitHistory {
    private Long habitId;
    private Date habitDate;
    private int numericValue;
}
