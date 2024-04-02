package com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid UserName or password , Try again..");
    }
    public InvalidCredentialsException(String message){
        super(message);
    }
}
