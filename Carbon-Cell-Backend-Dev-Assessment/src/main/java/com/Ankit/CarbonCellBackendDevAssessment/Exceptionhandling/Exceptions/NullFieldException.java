package com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions;

public class NullFieldException extends RuntimeException{
    public NullFieldException(){
        super("May be one or more fields are Null ,Please insert value");
    }
    public NullFieldException(String message){
        super(message);
    }
}
