package com.paypal.ho.impl;

import com.paypal.ho.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SMSService {

    @Autowired
    private SmsSender smsSender;

    @RequestMapping("/send-sms")
    public SMSResponse sendSMS(@RequestBody SMSRequest content) {
        System.out.println("sms content " + content);
        SMSResponse response = new SMSResponse();
        response.setStatus("success");
        smsSender.sendSMS(content);
        return response;
    }
}
