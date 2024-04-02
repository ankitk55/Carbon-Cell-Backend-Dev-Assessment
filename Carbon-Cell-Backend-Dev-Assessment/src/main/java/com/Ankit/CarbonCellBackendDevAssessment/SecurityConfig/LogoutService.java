package com.Ankit.CarbonCellBackendDevAssessment.SecurityConfig;

import com.Ankit.CarbonCellBackendDevAssessment.model.JwtTokenManagement;
import com.Ankit.CarbonCellBackendDevAssessment.repository.JwtTokenManagementRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class LogoutService implements LogoutHandler {
    @Autowired
    private  JwtTokenManagementRepo tokenManagementRepo;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
       final String authHeader = request.getHeader("Authorization");
       final String jwt;
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                response.setContentType("text/plain");
                response.getWriter().write(" Message : Missing Auth Header , provide auth header to logout");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return;
        }
            jwt =authHeader.substring(7);
        JwtTokenManagement tok =tokenManagementRepo.findByToken(jwt);
        if(tok !=null){
            tok.setTokenExpired(true);
            tokenManagementRepo.save(tok);
            try {
                response.setContentType("text/plain");
                response.getWriter().write(" Message :You have successfully  logout");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                response.setContentType("text/plain");
                response.getWriter().write(" Message :Invalid Token , provide Valid Token to successfull logout");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
