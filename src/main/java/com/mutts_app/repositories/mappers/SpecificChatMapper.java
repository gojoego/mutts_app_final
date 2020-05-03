package com.mutts_app.repositories.mappers;

import com.mutts_app.pojos.Message;
import com.mutts_app.pojos.SpecificChat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

// mapper interface contains SQL queries
// @Mapper annotation indicates that it is a mapper
// @Insert means adding to the appropriate SQL table
// @Select means pulling from SQL database

@Mapper
public interface SpecificChatMapper {

    String GET_MESSAGES_BY_CHAT_ID = "select c.id, m.message, m.dateSent, m.chatId, m.userId as senderId, c.chatTitle " +
            "from message m " +
            "join chats c " +
            "on c.id = m.chatId " +
            "where m.chatId = #{chatId} " +
            "order by id desc";

    String GET_CHAT_ID_BY_USER_IDS = "select uc.chatId " +
            "   from usersChats uc " +
            "   where uc.userId = #{param1} " +
            "   or uc.userId = #{param2} " +
            "   group by uc.chatId " +
            "   order by count(uc.chatId) desc " +
            "   limit 1";

    String SAVE_NEW_MESSAGE = "insert into `whatsapp`.message (message, chatId, userId) " +
            "VALUES (#{message}, #{chatId}, #{userId})";

    String CREATE_NEW_CHAT = "insert into `whatsapp`.chats (chatTitle) " +
            "VALUES (#{chatTitle})";

    @Select(GET_MESSAGES_BY_CHAT_ID)
    public ArrayList<SpecificChat> getMessagesByChatId(long chatId);

    @Select(GET_CHAT_ID_BY_USER_IDS)
    int getChatIdByUserIds(long userId, long otherUserId);

    @Insert(SAVE_NEW_MESSAGE)
    void saveNewMessage(Message msg);

    @Insert(CREATE_NEW_CHAT)
    int createNewChat(String chatTitle);





}


