package com.mutts_app.repositories;

import com.mutts_app.repositories.pojos.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    public Message findById(long id);

//    @Query("SELECT message from messages where chatId = :id order by id desc limit 1")
    public Message findFirst1ByChatIdOrderByIdDesc(int chatId);


    int inputNewMessage(Message msg);


}
