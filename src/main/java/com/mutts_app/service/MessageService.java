package com.mutts_app.service;

import com.mutts_app.exceptions.NewMessageException;
import com.mutts_app.repositories.MessageRepository;
import com.mutts_app.repositories.mappers.SpecificChatMapper;
import com.mutts_app.repositories.mappers.UserChatMapper;
import com.mutts_app.repositories.mappers.UserMapper;
import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.SpecificChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// service annotated with @Service and uses methods from repository or Mapper

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    SpecificChatMapper specificChatMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserChatMapper userChatMapper;

    public List<Message> getAllMessages() {
        return getAllMessages();
    }


    public ArrayList<SpecificChat> getSpecificChatsById(long userId, long otherUserId) {
        int chatId = specificChatMapper.getChatIdByUserIds(userId, otherUserId);
        return specificChatMapper.getMessagesByChatId(chatId);
    }


    public void createNewChat(int userId, int otherUserId) throws NewMessageException {
        ArrayList<String> names = userMapper.getUserFirstNames(userId, otherUserId);
        String title = names.get(0) + " & " + names.get(1);
        specificChatMapper.createNewChat(title);
        int chatId = userChatMapper.selectChatIdByChatTitle(title);
        userChatMapper.updateUserChats(userId, chatId);
        userChatMapper.updateUserChats(otherUserId, chatId);
    }

//    public ArrayList<SpecificChat> insertIntoMessageNewMessage(long userId, long otherUserId) throws NewMessageException {
//        int i = specificChatMapper.saveNewMessage(userId, otherUserId);
//        if (i == 1){
//            return specificChatMapper.getMessagesByChatId(userId);
//        } else {
//            NewMessageException nm = new NewMessageException("message not created");
//            throw nm;
//        }
//    }

    // create POST to save single message, insert into messages table, message, chatID, senderId and other fields in messages table
}