package com.paypal.ho.impl;

import com.paypal.ho.dao.Conversation;
import java.util.HashMap;
import java.util.Map;


public class ConversationStoreMemoryImpl implements ConversationStore {

    private static ConversationStore INSTANCE = new ConversationStoreMemoryImpl();

    private Map<String, Conversation> cache = new HashMap<>();

    private Map<String, String> feedbackCache = new HashMap<>();

    public static ConversationStore getInstance() {
        return INSTANCE;
    }

    @Override public void add(final Conversation conversation) {
        cache.put(conversation.getConvoID(), conversation);
    }

    @Override public void storeFeedback(final String feedbackId, final String feedbackContent) {
        System.out.println("The feedback was received from " + feedbackId + ", content is " + feedbackContent);
        feedbackCache.put(feedbackId, feedbackContent);
    }

    @Override
    public Conversation getConversationByID(String phoneNumber, int scheduleId) {
        return cache.get(phoneNumber);
    }

    @Override public Conversation getConversationByID(final String phoneNumber) {
        return null;
    }

    @Override public boolean updateConversation(final Conversation conversation) {
        return false;
    }
}
