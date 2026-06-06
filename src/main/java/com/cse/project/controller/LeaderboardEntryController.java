package com.cse.project.controller;

import com.cse.project.dto.Requests.LeaderboardEntryDTO;
import com.cse.project.entity.Leaderboard;
import com.cse.project.entity.LeaderboardEntry;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.LeaderboardEntryRepository;
import com.cse.project.repository.LeaderboardRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard-entries")
public class LeaderboardEntryController {

    @Autowired
    private LeaderboardEntryRepository entryRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/leaderboard/{leaderboardId}")
    public List<LeaderboardEntry> getEntriesByLeaderboard(@PathVariable Long leaderboardId) {
        return entryRepository.findByLeaderboardIdOrderByTotalScoreDesc(leaderboardId);
    }

    @PostMapping
    public ResponseEntity<LeaderboardEntry> addOrUpdateEntry(@RequestBody LeaderboardEntryDTO dto) {
        Leaderboard leaderboard = leaderboardRepository.findById(dto.getLeaderboardId())
                .orElseThrow(() -> new ResourceNotFoundException("Leaderboard not found with id " + dto.getLeaderboardId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        LeaderboardEntry entry = new LeaderboardEntry();
        entry.setLeaderboard(leaderboard);
        entry.setUser(user);
        entry.setRank(dto.getRank());
        entry.setTotalScore(dto.getTotalScore());
        entry.setWins(dto.getWins());

        LeaderboardEntry saved = entryRepository.save(entry);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
