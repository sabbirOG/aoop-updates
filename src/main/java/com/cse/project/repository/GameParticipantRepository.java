package com.cse.project.repository;

import com.cse.project.entity.GameParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameParticipantRepository extends JpaRepository<GameParticipant, Long> {
    List<GameParticipant> findBySessionId(Long sessionId);
}
