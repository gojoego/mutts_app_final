package com.mutts_app.repositories;

import com.mutts_app.pojos.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    public Message findById(int id);

    public Message findFirst1ByChatIdOrderByIdDesc(int chatId);

    public List<Message> findMessagesByUserId(int userId);


}
