package com.istore.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@AllArgsConstructor()
@Getter()
public class User {
    @Id()
    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String mobileNumber;

}
