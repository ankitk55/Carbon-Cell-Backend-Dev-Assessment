package com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptionhandler;

import com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.Exceptions.*;
import com.Ankit.CarbonCellBackendDevAssessment.Exceptionhandling.ExceptionsResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionsResponse> userNotFound(UserNotFoundException ex){
        String mess =ex.getMessage();
        ExceptionsResponse ap =ExceptionsResponse.builder().status(true).message(mess).httpStatus(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ap);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionsResponse> invalidcredentials(InvalidCredentialsException ex){
        String mess =ex.getMessage();
        ExceptionsResponse ap =ExceptionsResponse.builder().status(true).message(mess).httpStatus(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ap);
    }
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionsResponse> UserAlreadyExists(UserExistsException ex){
        String mess =ex.getMessage();
        ExceptionsResponse ap =ExceptionsResponse.builder().status(true).message(mess).httpStatus(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ap);
    }
    @ExceptionHandler(NullFieldException.class)
    public ResponseEntity<ExceptionsResponse> nullField(NullFieldException ex){
        String mess =ex.getMessage();
        ExceptionsResponse ap =ExceptionsResponse.builder().status(true).message(mess).httpStatus(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ap);
    }


}
