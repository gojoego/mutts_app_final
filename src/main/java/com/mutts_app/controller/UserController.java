package com.mutts_app.controller;


import com.mutts_app.model.CustomResponseObject;
import com.mutts_app.repositories.User;
import com.mutts_app.repositories.UserChats;
import com.mutts_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public CustomResponseObject<List<User>> getAllUsers(){
        CustomResponseObject<List<User>> obj = new CustomResponseObject();
        obj.setData(userService.getAllUsers());
        return obj;
    }

    @GetMapping("/{userId}")
    public CustomResponseObject<User> findUserById(@PathVariable("userId") long userId){
        CustomResponseObject<User> obj = new CustomResponseObject<>();
        obj.setData(userService.findUserById(userId));
        return obj;
    }

    @GetMapping("/{userId}/chats")
    public CustomResponseObject<List<UserChats>> findChatsByUserId(@PathVariable("userId") long userId){
        CustomResponseObject<List<UserChats>> obj = new CustomResponseObject<>();
        obj.setData(userService.findChatsByUserId(userId));
        return obj;
    }

}
