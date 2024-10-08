package com.lucifer.e_com_app.service;

import com.lucifer.e_com_app.models.User;
import com.lucifer.e_com_app.models.UserPrincipal;
import com.lucifer.e_com_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);
        if (user == null){
            System.out.println("User email not registered");
            throw new UsernameNotFoundException("User email not registered");
        }
        return new UserPrincipal(user);
    }
}
