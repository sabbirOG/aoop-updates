package com.cse.project.controller;

import com.cse.project.dto.Requests.CommunityDTO;
import com.cse.project.entity.Community;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.CommunityRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Community getCommunityById(@PathVariable Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Community not found with id " + id));
    }

    @PostMapping
    public ResponseEntity<Community> createCommunity(@RequestBody CommunityDTO dto) {
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner user not found with id " + dto.getOwnerId()));

        Community community = new Community();
        community.setName(dto.getName());
        community.setDescription(dto.getDescription());
        community.setIconUrl(dto.getIconUrl());
        community.setPublic(dto.isPublic());
        community.setOwner(owner);
        community.setCreatedAt(LocalDateTime.now());

        Community saved = communityRepository.save(community);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Community not found with id " + id));
        communityRepository.delete(community);
        return ResponseEntity.noContent().build();
    }
}
