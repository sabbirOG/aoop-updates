package com.cse.project.controller;

import com.cse.project.dto.Requests.GameParticipantDTO;
import com.cse.project.entity.GameParticipant;
import com.cse.project.entity.GameSession;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.GameParticipantRepository;
import com.cse.project.repository.GameSessionRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game-participants")
public class GameParticipantController {

    @Autowired
    private GameParticipantRepository participantRepository;

    @Autowired
    private GameSessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/session/{sessionId}")
    public List<GameParticipant> getParticipantsBySession(@PathVariable Long sessionId) {
        return participantRepository.findBySessionId(sessionId);
    }

    @PostMapping
    public ResponseEntity<GameParticipant> addParticipant(@RequestBody GameParticipantDTO dto) {
        GameSession session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Game session not found with id " + dto.getSessionId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        GameParticipant participant = new GameParticipant();
        participant.setSession(session);
        participant.setUser(user);
        participant.setScore(dto.getScore());
        participant.setResult(dto.getResult() != null ? dto.getResult() : "PLAYING");

        GameParticipant saved = participantRepository.save(participant);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameParticipant> updateParticipantResult(@PathVariable Long id, @RequestBody GameParticipantDTO dto) {
        GameParticipant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game participant not found with id " + id));

        participant.setScore(dto.getScore());
        if (dto.getResult() != null) {
            participant.setResult(dto.getResult());
        }
        GameParticipant updated = participantRepository.save(participant);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeParticipant(@PathVariable Long id) {
        GameParticipant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game participant not found with id " + id));
        participantRepository.delete(participant);
        return ResponseEntity.noContent().build();
    }
}
