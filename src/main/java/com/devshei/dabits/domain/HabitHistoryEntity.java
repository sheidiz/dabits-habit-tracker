package com.devshei.dabits.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="habit_history")
@IdClass(HabitHistoryEntityId.class)
public class HabitHistoryEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private HabitEntity habit;

    @Id
    @Column(name = "habit_date", nullable = false)
    private Date habitDate;

    @Column(name = "numeric_value", nullable = false)
    private int numericValue;
}
class HabitHistoryEntityId implements Serializable {
    private Long habit;
    private Date habitDate;
}