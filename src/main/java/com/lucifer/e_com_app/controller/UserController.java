package com.lucifer.e_com_app.controller;

import com.lucifer.e_com_app.models.User;
import com.lucifer.e_com_app.service.JwtService;
import com.lucifer.e_com_app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("hello")
    public String welcome(){
        return "Hello Abhishek";
    }

    @PostMapping("register")
    public User registerUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "Login Failed";
    }

}
