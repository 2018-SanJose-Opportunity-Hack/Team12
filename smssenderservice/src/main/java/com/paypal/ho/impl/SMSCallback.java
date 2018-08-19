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
        String fromPhoneNumber = normalizePhoneNumber(respMap.get(FROM));
        Conversation convo = ConversationStoreMemoryImpl.getInstance().getConversationByID(fromPhoneNumber);
        if (convo.getStatus() == 1) {
        	if (content.equalsIgnoreCase("y")) {
            	reply(fromPhoneNumber, "On a scale of 1-5, how happy are you with the meeting? (5 being extremely happy)");
            }
            else {
            	reply(fromPhoneNumber, "Would you like to reschedule your meeting?");
            }
        	convo.setStatus(2);
        } else if (convo.getStatus() == 2) {
        	storeFeedback(fromPhoneNumber, content);
        	reply(fromPhoneNumber, "Thank you!");
        }
        
    }

    private void storeFeedback(String fromPhoneNumber, String body2) {
		ConversationStoreMemoryImpl.getInstance().storeFeedback(fromPhoneNumber, body2);
		
	}

	private void reply(String fromPhoneNumber, String content) {
        SMSRequest smsRequest = new SMSRequest();
        smsRequest.setPhoneNumber(fromPhoneNumber);
        smsRequest.setContent(content);
        System.out.println(smsRequest.getPhoneNumber());
        System.out.println(smsRequest.getContent());
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
