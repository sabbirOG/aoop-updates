package com.cse.project.controller;

import com.cse.project.dto.Requests.LeaderboardDTO;
import com.cse.project.entity.Game;
import com.cse.project.entity.Leaderboard;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.GameRepository;
import com.cse.project.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leaderboards")
public class LeaderboardController {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game/{gameId}")
    public List<Leaderboard> getLeaderboardsByGame(@PathVariable Long gameId) {
        return leaderboardRepository.findByGameId(gameId);
    }

    @PostMapping
    public ResponseEntity<Leaderboard> createLeaderboard(@RequestBody LeaderboardDTO dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + dto.getGameId()));

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setGame(game);
        leaderboard.setPeriod(dto.getPeriod() != null ? dto.getPeriod() : "ALL_TIME");
        leaderboard.setUpdatedAt(LocalDateTime.now());

        Leaderboard saved = leaderboardRepository.save(leaderboard);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
