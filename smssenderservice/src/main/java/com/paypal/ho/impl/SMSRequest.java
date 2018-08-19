package com.paypal.ho.impl;

public class SMSRequest {

    private String content;
    private String phoneNumber;
    private int scheduleId;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(final int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override public String toString() {
        return "SMSRequest{" +
                "content='" + content + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", scheduleId=" + scheduleId +
                '}';
    }
}
