package com.spring.security.controller;

import com.spring.security.domain.AuthUser;
import com.spring.security.model.AuthenticationRequest;
import com.spring.security.model.AuthenticationResponse;
import com.spring.security.service.JWTService;
import com.spring.security.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public String registerUser(@RequestBody AuthUser user) {
        AuthUser registeredUser = authUserService.registerUser(user);
        return registeredUser.getName() + " User Registered Successfully!";
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse userAuthenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Bad Credentials!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String authToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().authenticationToken("Bearer " + authToken).build();
    }
}
