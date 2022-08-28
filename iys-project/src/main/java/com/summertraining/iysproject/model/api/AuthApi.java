package com.summertraining.iysproject.model.api;

import com.summertraining.iysproject.jwt.JwtTokenUtil;
import com.summertraining.iysproject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthApi {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            // Using AuthenticationManager, authenticate the coming request
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            /**
             * The identity of the principal being authenticated.
             * In the case of an authentication request with username and password,
             * this would be the username
             *
             * This technical description aside, the "principal" is the currently logged-in user.
             * So, basically we are retrieving the user.
             */
            Person person = (Person) authentication.getPrincipal();

            // Using the retrieved person, aka user, give them an access token
            String accessToken = jwtTokenUtil.generateAccessToken(person);

            AuthResponse response = new AuthResponse(person.getUsername(), accessToken);
            return ResponseEntity.ok(response);
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
