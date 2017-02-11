package com.samhita.samhitafirebase;

/**
 * Created by rahim on 11/2/17.
 */

public class UserMessage {

    private String message;
    private String user;

    public UserMessage(String message, String user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
