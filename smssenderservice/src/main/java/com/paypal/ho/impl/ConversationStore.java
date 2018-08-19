package com.paypal.ho.impl;

import com.paypal.ho.dao.Conversation;


public interface ConversationStore {

    void add(Conversation conversation);

    void storeFeedback(String phoneNumber, String feedbackContent);

    Conversation getConversationByID(String phoneNumber, int scheduleId);

    Conversation getConversationByID(String phoneNumber);

    boolean updateConversation(Conversation conversation);
}
