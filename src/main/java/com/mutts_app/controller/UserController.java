package com.mutts_app.controller;


import com.mutts_app.exceptions.NewMessageException;
import com.mutts_app.model.CustomResponseObject;
import com.mutts_app.repositories.pojos.Message;
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
    public CustomResponseObject<List<User>> getAllUsers() {
        CustomResponseObject<List<User>> obj = new CustomResponseObject();
        obj.setData(userService.getAllUsers());
        return obj;
    }

    @GetMapping("/{userId}")
    public CustomResponseObject<User> findUserById(@PathVariable("userId") long userId) {
        CustomResponseObject<User> obj = new CustomResponseObject<>();
        obj.setData(userService.findUserById(userId));
        return obj;
    }

    @GetMapping("/{userId}/chats")
    public CustomResponseObject<List<UserChats>> findChatsByUserId(@PathVariable("userId") long userId) {
        CustomResponseObject<List<UserChats>> obj = new CustomResponseObject<>();
        obj.setData(userService.findChatsByUserId(userId));
        return obj;
    }

    @GetMapping("/{userId}/chats/{otherUserId}")
    public CustomResponseObject<ArrayList<SpecificChat>> findSpecificChatsById(@PathVariable("userId") long userId,
                                                                               @PathVariable("otherUserId") long otherUserId) {
        CustomResponseObject<ArrayList<SpecificChat>> obj = new CustomResponseObject<>();
        obj.setData(messagesService.getSpecificChatsById(userId, otherUserId));
        return obj;
    }

    @PostMapping("/{userId}/chats/{otherUserId}")
    public CustomResponseObject<List<UserChats>> createNewChat(
            @PathVariable("userId") int userId,
            @PathVariable("otherUserId") int otherUserId) throws NewMessageException {
        CustomResponseObject<List<UserChats>> obj = new CustomResponseObject<>();
        // creating new chat with the two users
        messagesService.createNewChat(userId, otherUserId);
        // using response object
        // finding all chats by user and return the object as confirmation it was created
        obj.setData(userService.findChatsByUserId(userId));
        return obj;
    }

    @PostMapping("/{userId}/message")
    public CustomResponseObject<List<UserChats>> insertNewMessage(
            @PathVariable("userId") int userId,
            @RequestBody Message msg) throws NewMessageException {
        CustomResponseObject<List<UserChats>> obj = new CustomResponseObject<>();
        messagesService.saveNewMessage(msg);
        obj.setData(userService.findChatsByUserId(userId));
        return obj;
    }

    @GetMapping("/{userId}/message")
    public CustomResponseObject<List<UserChats>> findAllMessages(
            @PathVariable("userId") int userId){
        CustomResponseObject<List<UserChats>> obj = new CustomResponseObject<>();
        messagesService.findMessagesByUserId(userId);
        obj.setData(userService.findChatsByUserId(userId));
        return obj;
    }

    // Post Request from mutts_app
    // controller receives request
    // hits the PostMapping endpoint for messages between 2 users
    // controller calls methods in the service class
    // service invokes query in mapper


    // create new conversation
    // sync contact list

}


