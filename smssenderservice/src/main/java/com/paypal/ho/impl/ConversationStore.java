package com.paypal.ho.impl;

public interface ConversationStore {

    void add(SMSConversation conversation);

    void storeFeedback(String phoneNumbe, long timestamp, String feedbackContent);
}
