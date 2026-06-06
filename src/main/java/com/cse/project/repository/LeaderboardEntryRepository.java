package com.cse.project.repository;

import com.cse.project.entity.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, Long> {
    List<LeaderboardEntry> findByLeaderboardIdOrderByTotalScoreDesc(Long leaderboardId);
}
