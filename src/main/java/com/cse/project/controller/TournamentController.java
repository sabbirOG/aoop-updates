package com.cse.project.controller;

import com.cse.project.dto.Requests.TournamentDTO;
import com.cse.project.entity.Game;
import com.cse.project.entity.Tournament;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.GameRepository;
import com.cse.project.repository.TournamentRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id " + id));
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody TournamentDTO dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + dto.getGameId()));
        User organizer = userRepository.findById(dto.getOrganizerId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizer user not found with id " + dto.getOrganizerId()));

        Tournament tournament = new Tournament();
        tournament.setGame(game);
        tournament.setOrganizer(organizer);
        tournament.setName(dto.getName());
        tournament.setStatus(dto.getStatus() != null ? dto.getStatus() : "UPCOMING");
        tournament.setPrizePool(dto.getPrizePool());
        tournament.setStartDate(dto.getStartDate());
        tournament.setEndDate(dto.getEndDate());

        Tournament saved = tournamentRepository.save(tournament);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody TournamentDTO dto) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id " + id));

        if (dto.getName() != null) tournament.setName(dto.getName());
        if (dto.getStatus() != null) tournament.setStatus(dto.getStatus());
        if (dto.getPrizePool() > 0) tournament.setPrizePool(dto.getPrizePool());
        if (dto.getStartDate() != null) tournament.setStartDate(dto.getStartDate());
        if (dto.getEndDate() != null) tournament.setEndDate(dto.getEndDate());

        Tournament updated = tournamentRepository.save(tournament);
        return ResponseEntity.ok(updated);
    }
}
