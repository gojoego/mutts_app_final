package com.mutts_app.service;

import com.mutts_app.exceptions.NewMessageException;
import com.mutts_app.pojos.User;
import com.mutts_app.repositories.MessageRepository;
import com.mutts_app.repositories.UserRepository;
import com.mutts_app.mappers.SpecificChatMapper;
import com.mutts_app.mappers.UserChatMapper;
import com.mutts_app.mappers.UserMapper;
import com.mutts_app.pojos.Message;
import com.mutts_app.pojos.SpecificChat;
import com.mutts_app.response.SpecificChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// service annotated with @Service and uses methods from Repository or Mapper

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

    @Autowired
    UserRepository repo;

    public List<Message> findMessagesByUserId(int userId) {
//        return messageRepo.findMessagesByUserId(userId);
        List<Message> messages = messageRepo.findMessagesByUserId(userId);

        for (Message m: messages){
            m.setMessage(messageRepo.findById(userId).getMessage());

        }
        return messages;
    }

    public ArrayList<SpecificChat> getSpecificChatsById(long userId, long otherUserId) {
        int chatId = specificChatMapper.getChatIdByUserIds(userId, otherUserId);
        return specificChatMapper.getMessagesByChatId(chatId);
    }

    public SpecificChatResponse getMessagesByChatId(int chatId, long userId) {
        SpecificChatResponse obj = new SpecificChatResponse();
        obj.setMessages(specificChatMapper.getMessagesByChatId(chatId));
        User u = userMapper.getChatUserInfo(chatId, userId);
        obj.setFirstName(u.getFirstName());
        obj.setLastName(u.getLastName());
        obj.setPhotoUrl(u.getPhotoUrl());
        obj.setChatTitle(obj.getMessages().get(0).getChatTitle());
        return obj;
    }

    public void createNewChat(int userId, int otherUserId) throws NewMessageException {
        // creating list of strings of the first names of the 2 users involved in this new chat
        ArrayList<String> names = userMapper.getUserFirstNames(userId, otherUserId);
        // this becomes the new chatTitle in the `chats` table
        String title = names.get(0) + " & " + names.get(1);
        // inserts this new chatTitle into the `chats` table thereby creating the new row
        // the chatId is automatically created because of the auto increment
        specificChatMapper.createNewChat(title);
        // determine chatId using the title
        int chatId = userChatMapper.selectChatIdByChatTitle(title);
        // associate chatIds with both users
        userChatMapper.updateUserChats(userId, chatId);
        userChatMapper.updateUserChats(otherUserId, chatId);
    }

    public void saveNewMessage(Message msg) throws NewMessageException{
        specificChatMapper.saveNewMessage(msg);
    }


// create POST to save single message, insert into messages table, message, chatID, senderId and other fields in messages table
//    public ArrayList<SpecificChat> insertIntoMessageNewMessage(long userId, long otherUserId) throws NewMessageException {
//        int i = specificChatMapper.saveNewMessage(userId, otherUserId);
//        if (i == 1){
//            return specificChatMapper.getMessagesByChatId(userId);
//        } else {
//            NewMessageException nm = new NewMessageException("message not created");
//            throw nm;
//        }
//    }


}