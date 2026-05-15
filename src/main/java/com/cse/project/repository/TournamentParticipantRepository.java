package com.cse.project.repository;

import com.cse.project.entity.TournamentParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, Long> {
}
