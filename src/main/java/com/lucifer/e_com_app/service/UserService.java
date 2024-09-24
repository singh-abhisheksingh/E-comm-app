package com.lucifer.e_com_app.service;

import com.lucifer.e_com_app.models.User;
import com.lucifer.e_com_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);

    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
