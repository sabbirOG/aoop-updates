package com.cse.project.controller;

import com.cse.project.dto.Requests.TournamentParticipantDTO;
import com.cse.project.entity.Tournament;
import com.cse.project.entity.TournamentParticipant;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.TournamentParticipantRepository;
import com.cse.project.repository.TournamentRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tournament-participants")
public class TournamentParticipantController {

    @Autowired
    private TournamentParticipantRepository participantRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tournament/{tournamentId}")
    public List<TournamentParticipant> getParticipantsByTournament(@PathVariable Long tournamentId) {
        return participantRepository.findByTournamentId(tournamentId);
    }

    @PostMapping
    public ResponseEntity<TournamentParticipant> addParticipant(@RequestBody TournamentParticipantDTO dto) {
        Tournament tournament = tournamentRepository.findById(dto.getTournamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id " + dto.getTournamentId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        TournamentParticipant participant = new TournamentParticipant();
        participant.setTournament(tournament);
        participant.setUser(user);
        participant.setFinalRank(dto.getFinalRank());
        participant.setRegisteredAt(LocalDateTime.now());

        TournamentParticipant saved = participantRepository.save(participant);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentParticipant> updateParticipantRank(@PathVariable Long id, @RequestBody TournamentParticipantDTO dto) {
        TournamentParticipant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament participant record not found with id " + id));

        if (dto.getFinalRank() != null) {
            participant.setFinalRank(dto.getFinalRank());
        }
        TournamentParticipant updated = participantRepository.save(participant);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeParticipant(@PathVariable Long id) {
        TournamentParticipant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament participant record not found with id " + id));
        participantRepository.delete(participant);
        return ResponseEntity.noContent().build();
    }
}
