package com.mutts_app.repositories.mappers;

import com.mutts_app.repositories.pojos.Message;
import com.mutts_app.repositories.pojos.UserChats;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserChatMapper {
     String USER_CHATS_BY_ID = "select distinct(c.chatTitle) as chatName, c.id as chatId, max(m.dateSent) as dateSent, " +
             "m.userId as senderId " +
             "from chats c " +
             "join message m " +
             "on m.chatId = c.id " +
             "join usersChats uc " +
             "on uc.chatId = c.id " +
             "where uc.userId = #{userId} " +
             "group by chatId " +
             "order by dateSent desc";

     String INSERT_NEW_MESSAGE = "insert into `whatsapp`message (message, chatId, userId) " +
             "VALUES (#{message}, #{chatId}, #{userId})";

     String SELECT_CHAT_ID_BY_CHAT_TITLE = "select chatId from chats where chatTitle = #{chatTitle}";

    String UPDATE_USER_CHATS = "insert into `whatsapp`.usersChats (userId, chatId) VALUES (#{param1}, #{param2})";


    @Select(USER_CHATS_BY_ID)
    public List<UserChats> getChatsByUserId(long userId);


    @Insert(INSERT_NEW_MESSAGE)
    public void insertNewMessage(Message message);

    @Select(SELECT_CHAT_ID_BY_CHAT_TITLE)
    public int selectChatIdByChatTitle(String chatTitle);

    @Insert(UPDATE_USER_CHATS)
    void updateUserChats(int userId, int chatId);
}
