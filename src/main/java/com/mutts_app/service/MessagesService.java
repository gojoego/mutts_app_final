package com.mutts_app.service;

import com.mutts_app.repositories.MessagesRepository;
import com.mutts_app.repositories.pojos.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {

    @Autowired
    MessagesRepository repo;

    public List<Message> getAllMessages(){
        return getAllMessages();
    }


}