package com.mutts_app.repositories.mappers;

import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.SpecificChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

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

    String INSERT_INTO_CHATS_TABLE_FOR_NEW_MESSAGES = "insert into `whatsapp`.message (message, dateSent, chatId, userId) " +
            "VALUES (#{message}, #{dateSent}, #{chatId}, #{userId})";

    String INSERT_INTO_MESSAGE_TABLE_FOR_NEW_MESSAGES = "insert into `whatsapp`.chats (id, chatTitle) " +
            "            VALUES (#{id}, #{chatTitle})";

    @Select(GET_MESSAGES_BY_CHAT_ID)
    public ArrayList<SpecificChat> getMessagesByChatId(long chatId);

    @Select(GET_CHAT_ID_BY_USER_IDS)
    int getChatIdByUserIds(long userId, long otherUserId);

    @Select(INSERT_INTO_CHATS_TABLE_FOR_NEW_MESSAGES)
    int insertIntoChatNewMessage(long userId, long otherUserId);

    @Select(INSERT_INTO_MESSAGE_TABLE_FOR_NEW_MESSAGES)
    int insertIntoMessageNewMessage(long userId, long otherUserId);
}


