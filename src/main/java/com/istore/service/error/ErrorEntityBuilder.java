package com.istore.service.error;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("singleton")
@PropertySource("classpath:messages.properties")
public class ErrorEntityBuilder {
    @Value("${INTERNAL_SERVER_ERROR}")
    private String internalServerErrorMessage;
    @Value("${USER_NOT_FOUND}")
    private String userNotFoundMessage;
    @Value("${INVALID_PASSWORD}")
    private String invalidPasswordMessage;
    @Value("${USER_ALREADY_EXISTS}")
    private String userAlreadyExistsMessage;
    @Value("${USERNAME_REQUIRED}")
    private String usernameRequiredErrorMessage;
    @Value("${PASSWORD_REQUIRED}")
    private String passwordRequiredMessage;
    @Value("${EMAIL_REQUIRED}")
    private String emailRequiredMessage;
    @Value("${FIRSTNAME_REQUIRED}")
    private String firstNameRequiredMessage;
    @Value("${LASTNAME_REQUIRED}")
    private String lastnameRequiredMessage;
    @Value("${MOBILE_NUMBER_REQUIRED}")
    private String mobileNumberRequiredMessage;

    private HashMap<String, Error> codeToErrorMap;
    private HashMap<String, Error> internalServerErrorMap;

    @PostConstruct
    public void getErrorCodes() {
        codeToErrorMap = new HashMap<>();
        internalServerErrorMap = new HashMap<>();
        internalServerErrorMap.put(ErrorCodes.INTERNAL_SERVER_ERROR, Error.builder().errorMessage(internalServerErrorMessage).build());
        codeToErrorMap.put(ErrorCodes.USER_NOT_FOUND, Error.builder().errorMessage(userNotFoundMessage).build());
        codeToErrorMap.put(ErrorCodes.INVALID_PASSWORD, Error.builder().errorMessage(invalidPasswordMessage).build());
        codeToErrorMap.put(ErrorCodes.USER_ALREADY_EXIST, Error.builder().errorMessage(userAlreadyExistsMessage).build());
        codeToErrorMap.put(ErrorCodes.USERNAME_REQUIRED, Error.builder().errorMessage(usernameRequiredErrorMessage).build());
        codeToErrorMap.put(ErrorCodes.EMAIL_REQUIRED, Error.builder().errorMessage(userNotFoundMessage).build());
        codeToErrorMap.put(ErrorCodes.PASSWORD_REQUIRED, Error.builder().errorMessage(invalidPasswordMessage).build());
        codeToErrorMap.put(ErrorCodes.FIRSTNAME_REQUIRED, Error.builder().errorMessage(userAlreadyExistsMessage).build());
        codeToErrorMap.put(ErrorCodes.LASTNAME_REQUIRED, Error.builder().errorMessage(internalServerErrorMessage).build());
        codeToErrorMap.put(ErrorCodes.MOBILE_NUMBER_REQUIRED, Error.builder().errorMessage(userNotFoundMessage).build());
    }

    public Error getErrorEntity(String code) {
        return codeToErrorMap.get(code);
    }
     public Error getInternalServerErrorEntity(String code) {
        return internalServerErrorMap.get(code);
     }
}
