package com.mutts_app.service;

import com.mutts_app.repositories.MessageRepository;
import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.User;
import com.mutts_app.repositories.pojos.UserChats;
import com.mutts_app.repositories.UserRepository;
import com.mutts_app.repositories.mappers.UserChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    UserChatMapper chatRepo;

    @Autowired
    MessageRepository messageRepo;


    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User findUserById(long userId) {
        return  repo.findByUserId(userId);
    }

    public List<UserChats> findChatsByUserId(long userId) {
        List<UserChats> chats = chatRepo.getChatsByUserId(userId);

        for (UserChats u : chats){
            u.setPhotoUrl(repo.findByUserId(u.getSenderId()).getPhotoUrl());
            u.setMessage(messageRepo.findFirst1ByChatIdOrderByIdDesc((int) u.getChatId()).getMessage());
        }

        return chats;
    }


//    public Message inputNewMessage(Message msg, int userId){
//        UserChats i = chatRepo.inputNewChat();
//        return (Message) chatRepo.getChatsByUserId(msg.getId());
//
//    }

//    public UserChats createNewMessage(){
//        int i = chatRepo.createNewChat(chat);
//        int x = chatRepo.associateChat(id, chat.getId());
//        int y = chatRepo.associateChat(otherId, chat.getId());
//        return chat;
//    }


}

/*

Problem: each user chat was getting the same photo

    public List<UserChats> findChatsByUserId(long userId) {
        List<UserChats> chats = chatRepo.getChatsByUserId(userId);
        for (UserChats u : chats){
            u.setPhotoUrl(repo.findByUserId(userId).getPhotoUrl());
        }
        return chats;
    }

Solution: user "u.getSenderId()" instead of "userId"

    -using a for each loop and using the same userId over and over
        with previous code
    -using the "u" object assures that each unique picture will be
        used from the URL in the database

*/

