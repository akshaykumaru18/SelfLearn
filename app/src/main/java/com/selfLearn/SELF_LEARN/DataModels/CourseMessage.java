package com.selfLearn.SELF_LEARN.DataModels;

import com.google.firebase.Timestamp;

public  class CourseMessage {
    String messageId;
    String message;
    Timestamp messageTime;
    String sentBy;
    String messageType;


    public CourseMessage(String messageId, String message, Timestamp messageTime, String sentBy, String messageType) {
        this.messageId = messageId;
        this.message = message;
        this.messageTime = messageTime;
        this.sentBy = sentBy;
        this.messageType = messageType;

    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
