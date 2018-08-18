package com.paypal.ho.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class SMSCallback {

    private static final String BODY = "Body";

    private static final String FROM = "From";

    @Autowired
    private SMSService smsService;

    private static String normalizePhoneNumber(String phoneNumber) {
        return "+" + phoneNumber.substring(3);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return false;
    }

    @RequestMapping("/sms")
    public void callback(@RequestBody String body) {
        System.out.println("sms call back: " + body);
        Map<String, String> respMap = parseResp(body);
        String content = respMap.get(BODY);
        String fromPhoneNumber = respMap.get(FROM);
        reply(fromPhoneNumber, "Thank you!");
    }

    private void reply(String fromPhoneNumber, String content) {
        SMSRequest smsRequest = new SMSRequest();
        smsRequest.setPhoneNumber(normalizePhoneNumber(fromPhoneNumber));
        smsRequest.setContent(content);
        smsService.sendSMS(smsRequest);
    }

    private Map<String, String> parseResp(String body) {
        String[] splited = body.split("&");
        Map<String, String> respKV = new HashMap<>();
        if (splited != null) {
            for (String kvStr : splited) {
                String[] kv = kvStr.split("=");
                if (kv != null && kv.length == 2) {
                    respKV.put(kv[0], kv[1]);
                    //                    System.out.println("key " + kv[0] + " ,value" + kv[1]);
                }
            }
        }
        return respKV;
    }
}
