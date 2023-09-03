package com.istore.service.exception;

import com.istore.service.error.Error;
import com.istore.service.error.ErrorCodes;
import com.istore.service.error.ErrorEntityBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class IStoreExceptionHandler extends ResponseEntityExceptionHandler {
    private final ErrorEntityBuilder errorEntityBuilder;

    @ExceptionHandler(IStoreException.class)
    public ResponseEntity<Error> handleServiceException(IStoreException exception, WebRequest request) {
        String errorCode = exception.getMessage();
        log.info("In exception handler ++ " + errorCode);
        Error error = errorEntityBuilder.getErrorEntity(errorCode);
        if(error != null)
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        error = errorEntityBuilder.getErrorEntity(ErrorCodes.INTERNAL_SERVER_ERROR);
        if (exception.getCause() != null) {
            if (exception.getCause().toString().trim().length() >= 20) {
                error.setMoreInfo(exception.getCause().toString().trim().substring(0, 20));
            } else {
                error.setMoreInfo(exception.getCause().toString());
            }
            if (exception.getCause().toString().trim().length() >= 180) {
                error.setMoreInfo(exception.getCause().toString().trim().substring(0, 180));
            } else {
                error.setMoreInfo(exception.getCause().toString());
            }
        }
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Error> handleException(Throwable ex, WebRequest request) {
        log.error("Unexpected Error", ex);
        Error error = errorEntityBuilder.getInternalServerErrorEntity(ErrorCodes.INTERNAL_SERVER_ERROR);
        if (ex.toString().trim().length() >= 20) {
            error.setErrorMessage(ex.toString().trim().substring(0, 20));
        } else {
            error.setErrorMessage(ex.toString());
        }
        if (ex.toString().trim().length() >= 180) {
            error.setMoreInfo(ex.toString().trim().substring(0, 180));
        } else {
            error.setMoreInfo(ex.toString());
        }
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
