package com.lucifer.e_com_app.controller;

import com.lucifer.e_com_app.dto.LoginRequest;
import com.lucifer.e_com_app.models.User;
import com.lucifer.e_com_app.service.JwtService;
import com.lucifer.e_com_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to BuyAbhi";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null)
            return ResponseEntity.badRequest().body("Email already exists!");

        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        boolean isAuthenticated = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            String token = jwtService.generateToken(loginRequest.getEmail());
            return ResponseEntity.ok().body(token);
        }
        else {
            return ResponseEntity.badRequest().body("Invalid email or password!");
        }
    }

}
