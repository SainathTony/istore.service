package com.istore.service.service;

import com.istore.service.model.User;
import com.istore.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class AuthService {
    @Autowired()
    private UserRepository userRepository;

    public User getByEmail(String email) {
        Optional<User> user =  userRepository.findByEmail(email);
        return user.get();
    }

    public User createUser(User user) {
        User createdUser = userRepository.insert(user);
        return createdUser;
    }
}
