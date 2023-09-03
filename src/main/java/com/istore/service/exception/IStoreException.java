package com.istore.service.exception;

public class IStoreException extends Exception{
    public IStoreException(Throwable cause){super(cause);}
    public IStoreException(String message, Throwable cause){super(message, cause);}
    public IStoreException(String message){super(message);}
}
