package com.mutts_app.controller;


import com.mutts_app.model.CustomResponseObject;
import com.mutts_app.repositories.User;
import com.mutts_app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping
    public CustomResponseObject<List<User>> getAllUsers(){
        CustomResponseObject<List<User>> obj = new CustomResponseObject();
        obj.setData(userService.getAllUsers());
        return obj;
    }

}
