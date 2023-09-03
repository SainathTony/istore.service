package com.istore.service.controller;

import com.istore.service.error.ErrorCodes;
import com.istore.service.exception.IStoreException;
import com.istore.service.model.User;
import com.istore.service.payload.LoginRequest;
import com.istore.service.service.AuthService;
import com.istore.service.validation.AuthValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;
    @Autowired
    private final AuthValidation authValidation;

    @PostMapping("/login")
    public User login(@RequestBody() LoginRequest loginRequest) throws IStoreException {
        log.info("Login request received for username :: " +loginRequest.getUsername());
        authValidation.validateLogin(loginRequest);
        User user = authService.getByEmail(loginRequest.getUsername());
        if(user == null) {
            throw new IStoreException(ErrorCodes.USER_NOT_FOUND);
        }
        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw new IStoreException(ErrorCodes.INVALID_PASSWORD);
        }
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestBody() User registerRequest) throws IStoreException {
        log.info("register request for email :: " + registerRequest.getEmail());
        authValidation.validateRegistration(registerRequest);
        User user = authService.getByEmail(registerRequest.getEmail());
        if(user != null) {
            log.error("User with email " + registerRequest.getEmail() + " already exist");
            throw new IStoreException(ErrorCodes.USER_ALREADY_EXIST);
        }
        User new_user = authService.createUser(registerRequest);
        return new_user;
    }
}
