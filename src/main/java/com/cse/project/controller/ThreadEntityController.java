package com.cse.project.controller;

import com.cse.project.dto.Requests.ThreadDTO;
import com.cse.project.entity.Channel;
import com.cse.project.entity.ThreadEntity;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.ChannelRepository;
import com.cse.project.repository.ThreadEntityRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/threads")
public class ThreadEntityController {

    @Autowired
    private ThreadEntityRepository threadRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/channel/{channelId}")
    public List<ThreadEntity> getThreadsByChannel(@PathVariable Long channelId) {
        return threadRepository.findByChannelId(channelId);
    }

    @PostMapping
    public ResponseEntity<ThreadEntity> createThread(@RequestBody ThreadDTO dto) {
        Channel channel = channelRepository.findById(dto.getChannelId())
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id " + dto.getChannelId()));
        User starter = userRepository.findById(dto.getStarterUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Starter user not found with id " + dto.getStarterUserId()));

        ThreadEntity thread = new ThreadEntity();
        thread.setChannel(channel);
        thread.setStarterUser(starter);
        thread.setTitle(dto.getTitle());
        thread.setCreatedAt(LocalDateTime.now());

        ThreadEntity saved = threadRepository.save(thread);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long id) {
        ThreadEntity thread = threadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Thread not found with id " + id));
        threadRepository.delete(thread);
        return ResponseEntity.noContent().build();
    }
}
