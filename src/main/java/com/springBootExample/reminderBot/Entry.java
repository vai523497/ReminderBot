package com.springBootExample.reminderBot;

import java.sql.Timestamp;
import java.util.Set;

public class Entry {

	private Long id;
	private Timestamp time;

	private Set<Messaging> messaging;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Set<Messaging> getMessaging() {
		return messaging;
	}

	public void setMessaging(Set<Messaging> messaging) {
		this.messaging = messaging;
	}

}
