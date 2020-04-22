package com.mutts_app.service;

import com.mutts_app.repositories.MessageRepository;
import com.mutts_app.repositories.mappers.SpecificChatMapper;
import com.mutts_app.repositories.pojos.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository repo;

    @Autowired
    SpecificChatMapper chat;

    public List<Message> getAllMessages(){
        return getAllMessages();
    }


}