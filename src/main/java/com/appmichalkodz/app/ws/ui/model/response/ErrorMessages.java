package com.appmichalkodz.app.ws.ui.model.response;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXIST("Recor already exists"),
    INTERNAL_SERVER_ERRROR("Internal Server error"),
    MP_RECORD_FOUND("recortd with provided id not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("nie mozna zupdataowac recodtu"),
    COULD_NOT_DELETE_RECORD("nie mozna usunac rekordu"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    //return errorMessage

    public String getErrorMessage(){
        return this.errorMessage;
                    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
