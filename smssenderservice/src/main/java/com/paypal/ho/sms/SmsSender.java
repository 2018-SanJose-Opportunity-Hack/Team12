package com.paypal.ho.sms;

import com.paypal.ho.impl.SMSRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;


@Service
public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC910ed42f69eaacca0da79b1802fd1192";
    public static final String AUTH_TOKEN =
            "b90f831f8947d57b980e5007c41685a4";

    private String fromPhone = "+18317038841";

    public Message.Status sendSMS(SMSRequest smsRequest) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(smsRequest.getPhoneNumber()), // to
                        new PhoneNumber(fromPhone), // from
                        smsRequest.getContent())
                .create();

        return message.getStatus();
    }
}