package com.mutts_app.service;

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


    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User findUserById(long userId) {
        return  repo.findByUserId(userId);
    }

    public List<UserChats> findChatsByUserId(long userId) {
        List<UserChats> chats = chatRepo.getChatsByUserId(userId);
        for (UserChats u : chats){
            u.setPhotoUrl(repo.findByUserId(userId).getPhotoUrl());
        }
        return chats;

    }
}
