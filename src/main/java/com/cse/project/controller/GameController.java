package com.cse.project.controller;

import com.cse.project.dto.Requests.GameDTO;
import com.cse.project.entity.Game;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameDTO dto) {
        Game game = new Game();
        game.setTitle(dto.getTitle());
        game.setGenre(dto.getGenre());
        game.setMode(dto.getMode());
        game.setMaxPlayers(dto.getMaxPlayers());

        Game saved = gameRepository.save(game);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));
        gameRepository.delete(game);
        return ResponseEntity.noContent().build();
    }
}
