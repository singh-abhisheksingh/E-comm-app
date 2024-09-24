package com.lucifer.e_com_app.controller;

import com.lucifer.e_com_app.models.User;
import com.lucifer.e_com_app.service.JwtService;
import com.lucifer.e_com_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to BuyAbhi";
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getEmail());
        else
            return "Login Failed";
    }

}
