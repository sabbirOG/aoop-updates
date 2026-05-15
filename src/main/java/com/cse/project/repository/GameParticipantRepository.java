package com.cse.project.repository;

import com.cse.project.entity.GameParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameParticipantRepository extends JpaRepository<GameParticipant, Long> {
}
