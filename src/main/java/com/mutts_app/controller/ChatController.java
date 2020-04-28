package com.mutts_app.controller;

//create a new api endpoint:
//        /chats/1
//        this will return the same thing that the:
//        /users/3/chats/4
//        currently does
//        the chat detail
//        but it only requires the chatId
//        so you won’t need to look up the chatId in the “getUserChats” service method

import com.mutts_app.model.CustomResponseObject;
import com.mutts_app.repositories.pojos.SpecificChat;
import com.mutts_app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private MessageService messagesService;

    @RequestMapping("/{chatId}")
    public CustomResponseObject<List<SpecificChat>> findChatsByChatId (int chatId){
        CustomResponseObject<List<SpecificChat>> obj = new CustomResponseObject<>();
        obj.setData(messagesService.getMessagesByChatId(chatId));
        return obj;
    }
    // in order to create an end point, you need to have a mapping
    // controllers receives HTTP requests
    // this is a new way of getting chats to appear in the mutts_app window


}
