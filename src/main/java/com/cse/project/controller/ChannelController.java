package com.cse.project.controller;

import com.cse.project.dto.Requests.ChannelDTO;
import com.cse.project.entity.Channel;
import com.cse.project.entity.Community;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.ChannelRepository;
import com.cse.project.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/community/{communityId}")
    public List<Channel> getChannelsByCommunity(@PathVariable Long communityId) {
        return channelRepository.findByCommunityId(communityId);
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody ChannelDTO dto) {
        Community community = communityRepository.findById(dto.getCommunityId())
                .orElseThrow(() -> new ResourceNotFoundException("Community not found with id " + dto.getCommunityId()));

        Channel channel = new Channel();
        channel.setCommunity(community);
        channel.setName(dto.getName());
        channel.setType(dto.getType() != null ? dto.getType() : "TEXT");
        channel.setCreatedAt(LocalDateTime.now());

        Channel saved = channelRepository.save(channel);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id " + id));
        channelRepository.delete(channel);
        return ResponseEntity.noContent().build();
    }
}
