package com.mutts_app.repositories.mappers;

import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.SpecificChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface SpecificChatMapper {

    String query = "select c.id, m.message, m.dateSent, m.chatId, m.userId, c.chatTitle " +
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

    @Select(query)
    public ArrayList<SpecificChat> getMessagesByChatId(long chatId);

    @Select(GET_CHAT_ID_BY_USER_IDS)
    int getChatIdByUserIds(long userId, long otherUserId);
}


