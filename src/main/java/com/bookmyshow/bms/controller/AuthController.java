package com.bookmyshow.bms.controller;

import com.bookmyshow.bms.model.User;
import com.bookmyshow.bms.repository.UserRepository;
import com.bookmyshow.bms.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // POST /register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (username == null || password == null)
            return ResponseEntity.badRequest().body(Map.of("error", "username and password required"));

        if (userRepository.findByUsername(username).isPresent())
            return ResponseEntity.status(409).body(Map.of("error", "user exists"));

        User u = new User(username, password); // plaintext (insecure) as discussed
        userRepository.save(u);

        return ResponseEntity.ok(Map.of("message", "user created"));
    }

    // POST /login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (username == null || password == null)
            return ResponseEntity.badRequest().body(Map.of("error", "username and password required"));

        return userRepository.findByUsername(username)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        String token = jwtUtil.generateToken(username);
                        return ResponseEntity.ok(Map.of("token", token));
                    } else {
                        return ResponseEntity.status(401).body(Map.of("error", "invalid credentials"));
                    }
                })
                .orElseGet(() ->
                        ResponseEntity.status(401).body(Map.of("error", "invalid credentials")));
    }
}
