package com.example.moodyserver.controller;

import com.example.moodyserver.dto.*;
import com.example.moodyserver.entity.User;
import com.example.moodyserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody SignupRequestDTO dto
    ) {
        User saved = userService.signup(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @Valid @RequestBody LoginRequestDTO dto
    ) {
        User user = userService.login(dto.getUserName(), dto.getPassword());
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByName(@PathVariable String userName) {
        User u = userService.findByUserName(userName);
        return u != null
                ? ResponseEntity.ok(u)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(
            @RequestBody User userRequest
    ) {
        try {
            User updated = userService.updateInfo(
                    userRequest.getUserName(),
                    userRequest.getPassword(),
                    userRequest.getAddress()
            );
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}