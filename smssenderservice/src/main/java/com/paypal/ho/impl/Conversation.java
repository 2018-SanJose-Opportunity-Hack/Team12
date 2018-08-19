package com.paypal.ho.impl;

public class Conversation {
	String convoID = "";
	int status = 1;
	
	public Conversation(String ID) {
		convoID = ID;
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
	
	

}
