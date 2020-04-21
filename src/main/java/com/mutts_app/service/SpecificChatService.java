package com.mutts_app.service;

import com.mutts_app.repositories.mappers.SpecificChatMapper;
import com.mutts_app.repositories.pojos.SpecificChat;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificChatService {

    @Autowired
    SpecificChatMapper specificChat;

    public List<SpecificChat> getSpecificChats(long userId, long chatId){
        List<SpecificChat> specificChats = specificChat.getSpecificChatsById(userId, chatId);
        return specificChats;
    }

}
