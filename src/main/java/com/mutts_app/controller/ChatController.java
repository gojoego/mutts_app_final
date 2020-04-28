package com.mutts_app.controller;

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
    public CustomResponseObject<List<SpecificChat>> findChatsByChatId (){}

}
