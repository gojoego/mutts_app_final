package com.mutts_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository {

    public Messages findByMessagesId(long messagesId);

}
