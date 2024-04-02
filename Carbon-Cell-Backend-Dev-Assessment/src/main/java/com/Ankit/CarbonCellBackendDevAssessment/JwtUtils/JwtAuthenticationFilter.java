package com.Ankit.CarbonCellBackendDevAssessment.JwtUtils;

import com.Ankit.CarbonCellBackendDevAssessment.SecurityConfig.CustomUserDetailsService;
import com.Ankit.CarbonCellBackendDevAssessment.model.JwtTokenManagement;
import com.Ankit.CarbonCellBackendDevAssessment.repository.JwtTokenManagementRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenManagementRepo tokenManagementRepo;

    private static final List<String> NOT_SECURED_PATHS =
            Arrays.asList("/public/home", "/auth/login","/auth/register","/logout");
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getRequestURI();
          //  System.out.println("reqpath----------"+requestPath);
       if (! isSecuredPath(requestPath)) {

           String authHeader = request.getHeader("Authorization");

           String token = null;
           String username = null;
           if (authHeader == null) {
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               response.setContentType("text/plain");
               response.getWriter().write("Error Message : Missing Authorization Header....");
               return;

           }
           if (!authHeader.startsWith("Bearer ")) {
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               response.setContentType("text/plain");
               response.getWriter().write("Error Message : Invalid JWT token , Please check..");
               return;

           }


           token = authHeader.substring(7);

           try {
               username = jwtService.extractUsername(token);
           } catch (Exception ex) {
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               response.setContentType("text/plain");
               response.getWriter().write("Error Message : Invalid JWT token , Please check..");
               return;

           }
           if(isTokenExpired(token)){
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               response.setContentType("text/plain");
               response.getWriter().write("Error Message :  JWT token Expired..  Please login Again..");
               return;
           }

           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

               UserDetails userDetails = userDetailsService.loadUserByUsername(username);


               if (jwtService.validateToken(token, userDetails)) {
                   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authToken);
               }

           }
       }

        filterChain.doFilter(request, response);
    }
    private boolean isSecuredPath(String path) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        if(path.startsWith("/swagger-ui")|| path.startsWith("/v3/api-docs")||path.startsWith("/favicon.ico")){
            return true;
        }
        return NOT_SECURED_PATHS.stream().anyMatch(securedPath -> pathMatcher.match(securedPath, path));
    }
    private boolean isTokenExpired(String token){
      JwtTokenManagement tok= tokenManagementRepo.findByToken(token);
      if(tok!=null){

         if( !tok.tokenExpired()){
             return false;
         }
      }
      return true;
    }
}
