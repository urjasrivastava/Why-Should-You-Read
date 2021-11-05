package com.sdp.project.service;
import com.sdp.project.repository.UserRepository;
import com.sdp.project.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        if(user.getId()==null || user.getId().length()==0)
        {user.setId(UUID.randomUUID().toString());
         user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));}
        if(user.getRole()==null || user.getRole().length()==0)
            user.setRole("USER");
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}