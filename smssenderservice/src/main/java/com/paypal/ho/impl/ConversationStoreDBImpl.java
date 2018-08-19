package com.paypal.ho.impl;

import com.paypal.ho.dao.Conversation;
import com.paypal.ho.dao.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConversationStoreDBImpl implements ConversationStore {

    @Autowired
    DAOService daoService;

    @Override public void add(final Conversation conversation) {
        daoService.addConversaton(conversation);
    }

    @Override public void storeFeedback(final String phoneNumber, final String feedbackContent) {
        Conversation latestConversation = daoService.queryConversation(phoneNumber);
        //String content, int userId, int conversationId
        Feedback feedback = new Feedback(feedbackContent, latestConversation.getUserId(), latestConversation.getId(),
                phoneNumber);
        daoService.addFeedback(feedback);
    }

    @Override public Conversation getConversationByID(final String phoneNumber, int scheduleId) {
        return daoService.queryConversation(phoneNumber);
    }

    @Override public Conversation getConversationByID(final String phoneNumber) {
        return daoService.queryConversation(phoneNumber);
    }

    @Override public boolean updateConversation(final Conversation conversation) {
        return daoService.updateConversaton(conversation);
    }
}
