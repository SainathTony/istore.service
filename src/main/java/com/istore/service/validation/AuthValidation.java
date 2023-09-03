package com.istore.service.validation;

import com.istore.service.error.ErrorCodes;
import com.istore.service.exception.IStoreException;
import com.istore.service.model.User;
import com.istore.service.payload.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthValidation {
    public void validateLogin(LoginRequest loginRequest) throws IStoreException {
        if(loginRequest.getUsername() == null) {
            throw new IStoreException(ErrorCodes.USERNAME_REQUIRED);
        }
        if(loginRequest.getPassword() == null) {
            throw new IStoreException(ErrorCodes.PASSWORD_REQUIRED);
        }
    }

    public void validateRegistration(User request) throws IStoreException {
        if(request.getEmail() == null) {
            throw  new IStoreException(ErrorCodes.EMAIL_REQUIRED);
        }
        if(request.getPassword() == null) {
            throw  new IStoreException(ErrorCodes.PASSWORD_REQUIRED);
        }
        if(request.getFirstName() == null) {
            throw  new IStoreException(ErrorCodes.FIRSTNAME_REQUIRED);
        }
        if(request.getLastName() == null) {
            throw  new IStoreException(ErrorCodes.LASTNAME_REQUIRED);
        }
        if(request.getMobileNumber() == null) {
            throw  new IStoreException(ErrorCodes.MOBILE_NUMBER_REQUIRED);
        }
    }
}
