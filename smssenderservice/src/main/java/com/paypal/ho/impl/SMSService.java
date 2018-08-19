package com.paypal.ho.impl;

import com.paypal.ho.sms.SmsSender;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SMSService {

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private DAOService daoService;

    private boolean enabled = true;

    @RequestMapping("/send-sms")
    public SMSResponse sendSMS(@RequestBody SMSRequest content) {
        System.out.println("sms content " + content);
        SMSResponse response = new SMSResponse();
        response.setStatus("success");
        Conversation conversation = ConversationStoreMemoryImpl.getInstance()
                .getConversationByID(content.getPhoneNumber());
        if (conversation == null) {
            ConversationStoreMemoryImpl.getInstance().add(new Conversation(content.getPhoneNumber()));
        }
        if (enabled) {
            final Message.Status status = smsSender.sendSMS(content);
            response.setStatus(status.name());
        }
        return response;
    }
}
