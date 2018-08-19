package com.paypal.ho.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String convoID;

    private int scheduleId;

    private int questionId;

    /**
     * 1 initialized
     * 2 sending feedback
     * 3 complete the conversation
     * 4 reschedule
     * 5 reschedule completed
     */
    private int status = 1;

    private int userId;

    private long createTime;

    private long lastUpdateTime;

    public Conversation(int userId, String convoID, long createTime) {
        this.convoID = convoID;
        this.createTime = createTime;
        this.lastUpdateTime = createTime;
    }

    public Conversation(int userId, String convoID, long createTime, int scheduleId) {
        this.convoID = convoID;
        this.createTime = createTime;
        this.lastUpdateTime = createTime;
        this.scheduleId = scheduleId;
    }

    public Conversation(int id, int scheduleId, int status, int userId, String convoID,
            long createTime, long lastUpdateTime) {
        this.id = id;
        this.convoID = convoID;
        this.scheduleId = scheduleId;
        this.status = status;
        this.userId = userId;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getConvoID() {
        return convoID;
    }

    public void setConvoID(String convoID) {
        this.convoID = convoID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(final int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final long createTime) {
        this.createTime = createTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(final long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", convoID='" + convoID + '\'' +
                ", scheduleId=" + scheduleId +
                ", status=" + status +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
