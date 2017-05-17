package com.springBootExample.dto;

import com.springBootExample.dto.response.Message;
import com.springBootExample.dto.response.Recipient;

public class ResponseDTO {

	private Recipient recipient = new Recipient();

	private Message message = new Message();
	
	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "{recipient : " + recipient.getId() + ", message : " + message.getText() + "}";
	}
	
	

}
