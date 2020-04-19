package com.mutts_app.repositories.mappers;

import com.mutts_app.repositories.UserChats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper
public interface UserChatMapper {
     String query = "select distinct(c.chatTitle) as chatName, c.id as chatId, " +
            "m.userId as senderId " +
            "from chats c " +
            "join messages m " +
            "on m.chatId = c.id " +
            "join usersChats uc " +
            "on uc.chatId = c.id " +
            "where uc.userId = #{userId} " +
            "and m.userId != #{userId} " +
            "group by chatId, senderId " +
            "order by m.dateSent asc";



    @Select(query)
    public List<UserChats> getChatsByUserId(long userId);


}
