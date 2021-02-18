package com.julias.diary.diary.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julias.diary.diary.SpringApplicationContext;
import com.julias.diary.diary.service.UserService;
import com.julias.diary.diary.shared.dto.UserDto;
import com.julias.diary.diary.ui.model.request.UserLoginRequestModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.imageio.IIOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*
Spring security for the user authentication
 */

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)
            throws
            AuthenticationException{
     try{
         UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),
                 UserLoginRequestModel.class);

         return authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         creds.getEmail(),
                         creds.getPassword(),
                         new ArrayList<>())
         );
     } catch (IOException e){
         throw new RuntimeException(e);
     }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication auth) throws IOException, ServletException {
       String userName=((User) auth.getPrincipal()).getUsername();

       String token = Jwts.builder()
               .setSubject(userName)
               .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
               .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
               .compact();
      UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserDto userDto = userService.getUser(userName);
        resp.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        resp.addHeader("UserID", userDto.getUserId());
    }
}
