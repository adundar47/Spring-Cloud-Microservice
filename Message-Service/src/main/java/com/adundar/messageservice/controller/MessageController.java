package com.adundar.messageservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adundar.messageservice.exception.NotFoundException;
import com.adundar.messageservice.model.Message;
import com.adundar.messageservice.service.MessageService;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageService       messageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@Valid @RequestBody Message message) throws NotFoundException {
        LOG.debug("[createMessage]: Create Message request is received. Message: {}", message);
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessages() {
        LOG.debug("[getAllMessages]: Retrieve All Messages request is received.");
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> retrieveMessage(@PathVariable String messageId) throws NotFoundException {
        LOG.debug("[retrieveMessage]: Retrieve Message request is received. messageId: {}", messageId);
        return ResponseEntity.ok(messageService.retrieveMessage(messageId));
    }

    @GetMapping("/messages/name/{userName}")
    public ResponseEntity<?> retrieveUserMessages(@PathVariable String userName) {
        LOG.debug("[retrieveUserMessages]: Retrieve User Messages request is received. userName: {}", userName);
        return ResponseEntity.ok(messageService.retrieveUserMessages(userName));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable String messageId) throws NotFoundException {
        LOG.debug("[deleteMessage]: Delete Message request is received. messageId: {}", messageId);
        return ResponseEntity.ok(messageService.deleteMessage(messageId));
    }

}
