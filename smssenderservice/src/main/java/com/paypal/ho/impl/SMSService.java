package com.paypal.ho.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SMSService {

    @RequestMapping("/send-sms")
    public void sendSMS(@RequestParam(value = "content") String content) {

    }
}
