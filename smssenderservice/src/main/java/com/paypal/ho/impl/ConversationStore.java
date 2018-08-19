package com.paypal.ho.impl;

public interface ConversationStore {
    
    void add(Conversation conversation);

    void storeFeedback(String feedbackId, String feedbackContent);
    
    Conversation getConversationByID(String phoneNumber);

}
