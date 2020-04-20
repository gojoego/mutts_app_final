package com.mutts_app.repositories;

import com.mutts_app.repositories.pojos.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

    public Message findById(long id);

}
