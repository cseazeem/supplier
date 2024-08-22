package com.cseazeem.supplier.service;

import com.cseazeem.supplier.database.UserDao;
import com.cseazeem.supplier.entity.User;
import com.cseazeem.supplier.entity.dto.LoginUserDto;
import com.cseazeem.supplier.entity.dto.RegisterUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userJdbiDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input) {
        User user = new User(
                input.getFullName(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword())
        );

        userJdbiDao.save(user.getFullName(),user.getEmail(),user.getPassword());
        return user;
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userJdbiDao.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

