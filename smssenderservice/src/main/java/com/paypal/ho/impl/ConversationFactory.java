package com.paypal.ho.impl;

import com.paypal.ho.dao.Conversation;


public class ConversationFactory {

    private static int scheduleId = 1;

    public static Conversation newConversation(int userId, String conversationId) {
        Conversation conversation = new Conversation(userId, conversationId, System.currentTimeMillis());
        //        conversation.setScheduleId(scheduleId++); //FIXME not a thread safe
        return conversation;
    }

    public static Conversation newConversation(int userId, String conversationId,int scheduleId) {
        Conversation conversation = new Conversation(userId, conversationId, System.currentTimeMillis(), scheduleId);
        //        conversation.setScheduleId(scheduleId++); //FIXME not a thread safe
        return conversation;
    }
}
