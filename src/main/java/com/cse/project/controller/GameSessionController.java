package com.cse.project.controller;

import com.cse.project.dto.Requests.GameSessionDTO;
import com.cse.project.entity.Game;
import com.cse.project.entity.GameSession;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.GameRepository;
import com.cse.project.repository.GameSessionRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/game-sessions")
public class GameSessionController {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<GameSession> getAllSessions() {
        return gameSessionRepository.findAll();
    }

    @GetMapping("/{id}")
    public GameSession getSessionById(@PathVariable Long id) {
        return gameSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game session not found with id " + id));
    }

    @PostMapping
    public ResponseEntity<GameSession> createSession(@RequestBody GameSessionDTO dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + dto.getGameId()));
        User host = userRepository.findById(dto.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host user not found with id " + dto.getHostId()));

        GameSession session = new GameSession();
        session.setGame(game);
        session.setHost(host);
        session.setStatus(dto.getStatus() != null ? dto.getStatus() : "WAITING");
        session.setStartedAt(LocalDateTime.now());

        GameSession saved = gameSessionRepository.save(session);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameSession> updateSession(@PathVariable Long id, @RequestBody GameSessionDTO dto) {
        GameSession session = gameSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game session not found with id " + id));

        if (dto.getStatus() != null) {
            session.setStatus(dto.getStatus());
            if ("COMPLETED".equalsIgnoreCase(dto.getStatus()) || "ENDED".equalsIgnoreCase(dto.getStatus())) {
                session.setEndedAt(LocalDateTime.now());
            }
        }
        GameSession updated = gameSessionRepository.save(session);
        return ResponseEntity.ok(updated);
    }
}
