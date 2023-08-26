package com.istore.service.controller;

import com.istore.service.model.User;
import com.istore.service.payload.LoginRequest;
import com.istore.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired()
    private AuthService authService;

    @PostMapping("/login")
    public User login(@RequestBody() LoginRequest loginRequest) throws Exception {
        User user = authService.getByEmail(loginRequest.getUsername());
        if(user == null) {
            throw new Exception("User not found.");
        }
        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw new Exception("Invalid password.");
        }
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestBody() User registerRequest) throws Exception {
        User user = authService.createUser(registerRequest);
        return user;
    }
}
