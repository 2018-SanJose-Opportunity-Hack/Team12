package com.paypal.ho.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private long timestamp;

    private int userId;

    private int conversationId;

    private String phoneNumber;

    private int questionId = 0;

    public Feedback(String content, int userId, int conversationId, String phoneNumber) {
        this.content = content;
        this.userId = userId;
        this.conversationId = conversationId;
        this.phoneNumber = phoneNumber;
        this.timestamp = System.currentTimeMillis();
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(final int conversationId) {
        this.conversationId = conversationId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(final int questionId) {
        this.questionId = questionId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     timestamp long ,
     questionId int,
     userId id,
     phonenumber varchar(20),
     content varchar(200)
     */
}
