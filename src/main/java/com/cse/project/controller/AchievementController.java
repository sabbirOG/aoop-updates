package com.cse.project.controller;

import com.cse.project.dto.Requests.AchievementDTO;
import com.cse.project.entity.Achievement;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.AchievementRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}")
    public List<Achievement> getAchievementsByUser(@PathVariable Long userId) {
        return achievementRepository.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Achievement> grantAchievement(@RequestBody AchievementDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        Achievement achievement = new Achievement();
        achievement.setUser(user);
        achievement.setTitle(dto.getTitle());
        achievement.setDescription(dto.getDescription());
        achievement.setAwardedAt(LocalDateTime.now());

        Achievement saved = achievementRepository.save(achievement);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
