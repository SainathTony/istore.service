package com.istore.service.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor()
@Getter()
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String password;
}
