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
            "where m.userId = #{param1} and c.id = #{param2} " +
            "order by id desc";


    @Select(query)
    public ArrayList<SpecificChat> getSpecificChatsById(long userId, long chatId);

}


