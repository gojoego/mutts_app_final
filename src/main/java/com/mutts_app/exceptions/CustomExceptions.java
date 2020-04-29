package com.mutts_app.exceptions;

public class CustomExceptions {

    public static void ChatException() throws CustomException {
        throw new CustomException("no chats found", 400);
    }
}

class CustomException extends Exception {

    int statusCode;

    public CustomException(String msg, int statusCode) {
        super(msg);
    }
}