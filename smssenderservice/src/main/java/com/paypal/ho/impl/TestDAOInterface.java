package com.paypal.ho.impl;

import com.paypal.ho.dao.Conversation;
import com.paypal.ho.dao.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestDAOInterface {

    @Autowired
    DAOService daoService;

    @RequestMapping("/dao")
    public String test() {
        System.out.println("test it");
        final Conversation rst = daoService.queryConversation("+11234567890");
        System.out.println("Conversation is: " + rst);
        return "success";
    }

    @RequestMapping("/dao-insert")
    public String insert() {
        System.out.println("insert it");
        //        daoService.selectAllUsers();
        final Conversation conversation = ConversationFactory.newConversation(1, "+11234567890");
        conversation.setScheduleId(123);
        conversation.setUserId(1);
        daoService.addConversaton(conversation);
        //String content, int userId, int conversationId, String phoneNumber
        final Feedback feedback = new Feedback("content 1", 1, 1, "+12313212313");
        daoService.addFeedback(feedback);

        Conversation conversation1 = daoService.queryConversation("+16503058266");
        conversation1.setStatus(5);
        daoService.updateConversaton(conversation);
        return "success";
    }

}
