package com.mutts_app.service;

import com.mutts_app.exceptions.NewMessageException;
import com.mutts_app.repositories.MessageRepository;
import com.mutts_app.repositories.mappers.SpecificChatMapper;
import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.SpecificChat;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// service annotated with @Service and uses methods from repository or Mapper

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


    public ArrayList<SpecificChat> insertIntoChatNewMessage(long userId, long otherUserId) throws NewMessageException {

        int i = chatRepo.insertIntoChatNewMessage(userId, otherUserId);

        if (i == 1){
            return chatRepo.getMessagesByChatId(userId);
        } else {
            NewMessageException nm = new NewMessageException("message not created");
            throw nm;
        }
    }

    public ArrayList<SpecificChat> insertIntoMessageNewMessage(long userId, long otherUserId) throws NewMessageException {
        int i = chatRepo.insertIntoMessageNewMessage(userId, otherUserId);
        if (i == 1){
            return chatRepo.getMessagesByChatId(userId);
        } else {
            NewMessageException nm = new NewMessageException("message not created");
            throw nm;
        }
    }
}