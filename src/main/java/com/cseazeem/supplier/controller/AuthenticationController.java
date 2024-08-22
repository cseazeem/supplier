package com.cseazeem.supplier.controller;


import com.cseazeem.supplier.entity.User;
import com.cseazeem.supplier.entity.dto.LoginUserDto;
import com.cseazeem.supplier.entity.dto.RegisterUserDto;
import com.cseazeem.supplier.responses.LoginResponse;
import com.cseazeem.supplier.service.AuthenticationService;
import com.cseazeem.supplier.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // Authenticate the user using the provided credentials
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Generate the JWT token for the authenticated user
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Prepare the login response with the token and its expiration time
        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}

