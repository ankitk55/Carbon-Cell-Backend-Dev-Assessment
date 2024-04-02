package com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(){
        super("User Already Exists Try with Another UserName");
    }
    public UserExistsException(String message){
        super(message);
    }
}
