package com.mutts_app.response;

import com.mutts_app.pojos.SpecificChat;

import java.util.ArrayList;

public class SpecificChatResponse {

    String chatTitle;
    String firstName;
    String lastName;
    ArrayList<SpecificChat> messages;
    String photoUrl;

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<SpecificChat> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<SpecificChat> messages) {
        this.messages = messages;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
