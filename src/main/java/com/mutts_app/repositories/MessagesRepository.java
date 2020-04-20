package com.mutts_app.repositories;

import com.mutts_app.repositories.pojos.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository {

    public Messages findByMessagesId(long messagesId);

}
