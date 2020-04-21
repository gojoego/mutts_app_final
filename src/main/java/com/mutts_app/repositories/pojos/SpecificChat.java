package com.mutts_app.repositories.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SpecificChat {

    long id;            // chats.id, select c.id from chats
    String message;     // message.message, m.message from message
    String dateSent;    // message.dateSent, m.dateSent from message
    int chatId;         // message.chatId, m.chatId from message
    int senderId;       // message.userId, m.userId from message
    String chatTitle;   // chats.chatTitle, c.chatTitle from chats

    public SpecificChat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }
}
