package com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not Found");
    }
    public UserNotFoundException(String message){
        super(message);
    }
}
