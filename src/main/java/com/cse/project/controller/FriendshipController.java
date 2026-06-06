package com.cse.project.controller;

import com.cse.project.dto.Requests.FriendshipDTO;
import com.cse.project.entity.Friendship;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.FriendshipRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Friendship> getAllFriendships() {
        return friendshipRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Friendship> getFriendshipsForUser(@PathVariable Long userId) {
        return friendshipRepository.findByUserIdOrFriendId(userId, userId);
    }

    @PostMapping
    public ResponseEntity<Friendship> createFriendship(@RequestBody FriendshipDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
        User friend = userRepository.findById(dto.getFriendId())
                .orElseThrow(() -> new ResourceNotFoundException("Friend user not found with id " + dto.getFriendId()));

        Friendship friendship = new Friendship();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendship.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        friendship.setCreatedAt(LocalDateTime.now());

        Friendship saved = friendshipRepository.save(friendship);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friendship> updateFriendshipStatus(@PathVariable Long id, @RequestBody FriendshipDTO dto) {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found with id " + id));

        if (dto.getStatus() != null) {
            friendship.setStatus(dto.getStatus());
        }
        Friendship updated = friendshipRepository.save(friendship);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found with id " + id));
        friendshipRepository.delete(friendship);
        return ResponseEntity.noContent().build();
    }
}
