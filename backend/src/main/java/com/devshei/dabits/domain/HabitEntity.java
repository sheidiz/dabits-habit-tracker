package com.devshei.dabits.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "habits")
public class HabitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @Column(name = "habit_type", nullable = false, length = 10)
    private String habitType;

    @Column(nullable = false, length = 50)
    private String title;

    @Column
    private String question;

    @Column(name = "habit_description", length = 100)
    private String description;

    @Column(nullable = false)
    private String frequency;

    @Column(length = 10)
    private String measurement;

    @Column(length = 5)
    private String unit;

    @Column(nullable = false)
    @CurrentTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

}
