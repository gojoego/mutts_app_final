package com.mutts_app.controller;


import com.mutts_app.exceptions.NewMessageException;
import com.mutts_app.model.CustomResponseObject;
import com.mutts_app.repositories.pojos.SpecificChat;
import com.mutts_app.repositories.pojos.User;
import com.mutts_app.repositories.pojos.UserChats;
import com.mutts_app.service.MessageService;
import com.mutts_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messagesService;

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

    @GetMapping("/{userId}/chats/{otherUserId}")
    public CustomResponseObject<ArrayList<SpecificChat>> findSpecificChatsById(@PathVariable("userId") long userId,
                                                                          @PathVariable("otherUserId") long otherUserId){
        CustomResponseObject<ArrayList<SpecificChat>> obj = new CustomResponseObject<>();
        obj.setData(messagesService.getSpecificChatsById(userId, otherUserId));
        return obj;
    }

    @PostMapping("/{userId}/chats/{otherUserId}")
    public CustomResponseObject<List<UserChats>> createNewChat(
            @PathVariable("userId") int userId,
            @PathVariable("otherUserId") int otherUserId) throws NewMessageException {
        CustomResponseObject<List<UserChats>> obj = new CustomResponseObject<>();
        messagesService.createNewChat(userId,otherUserId);
        obj.setData(userService.findChatsByUserId(userId));
        return obj;
    }

}


