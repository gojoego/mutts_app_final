package com.mutts_app.service;

import com.mutts_app.repositories.MessageRepository;
import com.mutts_app.repositories.mappers.SpecificChatMapper;
import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.SpecificChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository repo;

    @Autowired
    SpecificChatMapper chat;

    @Autowired
    SpecificChatMapper chatRepo;

    public List<Message> getAllMessages(){
        return getAllMessages();
    }


    public ArrayList<SpecificChat> getSpecificChatsById(long userId, long otherUserId) {
        int chatId = chatRepo.getChatIdByUserIds(userId, otherUserId);
        return chatRepo.getMessagesByChatId(chatId);
    }
}