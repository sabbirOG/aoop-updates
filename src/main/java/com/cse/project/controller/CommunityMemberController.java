package com.cse.project.controller;

import com.cse.project.dto.Requests.CommunityMemberDTO;
import com.cse.project.entity.Community;
import com.cse.project.entity.CommunityMember;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.CommunityMemberRepository;
import com.cse.project.repository.CommunityRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/community-members")
public class CommunityMemberController {

    @Autowired
    private CommunityMemberRepository communityMemberRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/community/{communityId}")
    public List<CommunityMember> getMembersByCommunity(@PathVariable Long communityId) {
        return communityMemberRepository.findByCommunityId(communityId);
    }

    @PostMapping
    public ResponseEntity<CommunityMember> addMember(@RequestBody CommunityMemberDTO dto) {
        Community community = communityRepository.findById(dto.getCommunityId())
                .orElseThrow(() -> new ResourceNotFoundException("Community not found with id " + dto.getCommunityId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        CommunityMember member = new CommunityMember();
        member.setCommunity(community);
        member.setUser(user);
        member.setRole(dto.getRole() != null ? dto.getRole() : "MEMBER");
        member.setJoinedAt(LocalDateTime.now());

        CommunityMember saved = communityMemberRepository.save(member);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityMember> updateMemberRole(@PathVariable Long id, @RequestBody CommunityMemberDTO dto) {
        CommunityMember member = communityMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Community member record not found with id " + id));

        if (dto.getRole() != null) {
            member.setRole(dto.getRole());
        }
        CommunityMember updated = communityMemberRepository.save(member);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMember(@PathVariable Long id) {
        CommunityMember member = communityMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Community member record not found with id " + id));
        communityMemberRepository.delete(member);
        return ResponseEntity.noContent().build();
    }
}
