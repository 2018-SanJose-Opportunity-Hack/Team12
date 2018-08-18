package com.paypal.ho.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC910ed42f69eaacca0da79b1802fd1192";
    public static final String AUTH_TOKEN =
            "b90f831f8947d57b980e5007c41685a4";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+16692462510"), // to
                        new PhoneNumber("+18317038841"), // from
                        "test send a SMS ... ")
                .create();

        System.out.println(message.getSid());
    }
}