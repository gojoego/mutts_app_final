package com.mutts_app.service;

import com.mutts_app.repositories.RoleRepository;
import com.mutts_app.repositories.UserRepository;
import com.mutts_app.repositories.pojos.Role;
import com.mutts_app.repositories.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserLoginService {

    @Autowired
    UserRepository repo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;


    public void saveUser(User user) {
        user.setActive(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        repo.save(user);
    }

    public User findUserByEmail(String email) {
        return repo.findByEmail(email);
    }

}
