package com.mutts_app.repositories.pojos;

public class UserChats {

    String chatName;    // `whatsapp`.chats.chatTitle
    long chatId;        // `whatsapp`.chats.id
    long senderId;      // `whatsapp`.message.userId
    String dateSent;    // `whatsapp`.message.dateSent
    String photoUrl;    // `whatsapp`.user.photoUrl
    String message;     // `whatsapp`.message.message

    public UserChats() {
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
