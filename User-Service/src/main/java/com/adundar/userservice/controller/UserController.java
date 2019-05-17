package com.adundar.userservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adundar.userservice.exception.AlreadyExistException;
import com.adundar.userservice.exception.BadRequestException;
import com.adundar.userservice.exception.NotFoundException;
import com.adundar.userservice.model.User;
import com.adundar.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService          userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws AlreadyExistException {
        LOG.debug("[createUser]: Create User request is received. User: {}", user);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        LOG.debug("[getAllUsers]: Retrieve All Users request is received.");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> retrieveUser(@PathVariable String userId) throws NotFoundException {
        LOG.debug("[retrieveUser]: Retrieve User request is received. userId: {}", userId);
        return ResponseEntity.ok(userService.retrieveUser(userId));
    }

    @GetMapping("/users/name/{name}")
    public ResponseEntity<?> retrieveUserByName(@PathVariable String name) throws NotFoundException {
        LOG.debug("[retrieveUserByName]: Retrieve User request is received. name: {}", name);
        return ResponseEntity.ok(userService.retrieveUserByName(name));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) throws NotFoundException {
        LOG.debug("[deleteUser]: Delete User request is received. userId: {}", userId);
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userId)
            throws BadRequestException, NotFoundException {
        LOG.debug("[updateUser]: Update User request is received. Device: {}, userId: {}", user, userId);
        return ResponseEntity.ok(userService.updateUser(user, userId));

    }
}
