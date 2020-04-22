package com.mutts_app.repositories.mappers;

import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.UserChats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserChatMapper {
     String USER_CHATS_BY_ID = "select distinct(c.chatTitle) as chatName, c.id as chatId, " +
            "m.userId as senderId " +
            "from chats c " +
            "join message m " +
            "on m.chatId = c.id " +
            "join usersChats uc " +
            "on uc.chatId = c.id " +
            "where uc.userId = #{userId} " +
            "and m.userId != #{userId} " +
            "group by chatId, senderId " +
            "order by m.dateSent asc";

     String INPUT_CHAT = "insert into message message, chatID, userId";



    @Select(USER_CHATS_BY_ID)
    public List<UserChats> getChatsByUserId(long userId);


    @Select(INPUT_CHAT)
    public UserChats inputNewChat(Message message);
}
