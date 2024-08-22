package com.cseazeem.supplier.service;

import com.cseazeem.supplier.database.UserDao;
import com.cseazeem.supplier.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userJdbiDao;

    public List<User> allUsers() {
        return userJdbiDao.findAll();
    }
}

