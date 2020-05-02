package com.mutts_app.controller;

import com.mutts_app.response.CustomResponseObject;
import com.mutts_app.repositories.pojos.SpecificChat;
import com.mutts_app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*

new endpoint: /chats/{userId}
will return same results as /users/{userId}/chats/{senderId}
but will only require the chatId
no need to look up chatId in "getUserChats" service method

*/

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private MessageService messagesService;

    @RequestMapping("/{chatId}")
    public CustomResponseObject<List<SpecificChat>> findChatsByChatId (@PathVariable int chatId){
        CustomResponseObject<List<SpecificChat>> obj = new CustomResponseObject<>();
        obj.setData(messagesService.getMessagesByChatId(chatId));
        return obj;
    }
    // in order to create an end point, you need to have a mapping
    // controllers receives HTTP requests
    // this is a new way of getting chats to appear in the mutts_app window
    // update javascript code to reflect new endpoint


}
