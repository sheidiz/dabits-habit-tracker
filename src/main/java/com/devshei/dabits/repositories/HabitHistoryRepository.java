package com.devshei.dabits.repositories;

import com.devshei.dabits.domain.HabitHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitHistoryRepository extends JpaRepository<HabitHistoryEntity, Long> {
}
