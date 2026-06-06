package com.cse.project.controller;

import com.cse.project.dto.Requests.MessageDTO;
import com.cse.project.entity.Message;
import com.cse.project.entity.ThreadEntity;
import com.cse.project.entity.User;
import com.cse.project.exception.ResourceNotFoundException;
import com.cse.project.repository.MessageRepository;
import com.cse.project.repository.ThreadEntityRepository;
import com.cse.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ThreadEntityRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/thread/{threadId}")
    public List<Message> getMessagesByThread(@PathVariable Long threadId) {
        return messageRepository.findByThreadId(threadId);
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageDTO dto) {
        ThreadEntity thread = threadRepository.findById(dto.getThreadId())
                .orElseThrow(() -> new ResourceNotFoundException("Thread not found with id " + dto.getThreadId()));
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender user not found with id " + dto.getSenderId()));

        Message message = new Message();
        message.setThread(thread);
        message.setSender(sender);
        message.setContent(dto.getContent());
        message.setEdited(false);
        message.setSentAt(LocalDateTime.now());

        Message saved = messageRepository.save(message);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> editMessage(@PathVariable Long id, @RequestBody MessageDTO dto) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id " + id));

        if (dto.getContent() != null) {
            message.setContent(dto.getContent());
            message.setEdited(true);
        }
        Message updated = messageRepository.save(message);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id " + id));
        messageRepository.delete(message);
        return ResponseEntity.noContent().build();
    }
}
