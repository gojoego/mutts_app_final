package com.mutts_app.service;

import com.mutts_app.repositories.pojos.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {

    @Autowired
    Messages repo;

    public List<Messages> getAllMessages(){
        return getAllMessages();
    }


}