package com.devshei.dabits.repositories;

import com.devshei.dabits.domain.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {
}
