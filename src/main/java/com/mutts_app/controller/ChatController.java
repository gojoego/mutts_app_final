package com.mutts_app.controller;

import com.mutts_app.response.CustomResponseObject;
import com.mutts_app.pojos.SpecificChat;
import com.mutts_app.response.SpecificChatResponse;
import com.mutts_app.service.MessageService;
import com.mutts_app.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private UserLoginService loginService;

    @Autowired
    private MessageService messagesService;

    @RequestMapping("/{chatId}")
    public CustomResponseObject<SpecificChatResponse> findChatsByChatId (@PathVariable int chatId, Authentication auth){
        long user_id = loginService.findUserByEmail(auth.getName()).getUserId();
        SpecificChatResponse obj = messagesService.getMessagesByChatId(chatId, user_id);
        CustomResponseObject<SpecificChatResponse> obj2 = new CustomResponseObject<>();
        obj2.setData(obj);
        return obj2;
    }
    // in order to create an end point, you need to have a mapping
    // controllers receives HTTP requests
    // this is a new way of getting chats to appear in the mutts_app window
    // update javascript code to reflect new endpoint


}
