package com.appmichalkodz.app.ws.exceotions;

public class UserServiceException extends RuntimeException{
    private static final long  serialVersionUID = 2793028093548223965L;

    public UserServiceException(String message) {
        super(message);
    }

}
