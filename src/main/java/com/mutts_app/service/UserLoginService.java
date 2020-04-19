package com.mutts_app.service;

import com.mutts_app.repositories.User;
import com.mutts_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserLoginService {

    @Autowired
    UserRepository repo;




    public void saveUser(User user) {
    }

    public long findUserIdByEmail(String email) {
        return repo.findByEmail(email).getUserId();
    }
}
