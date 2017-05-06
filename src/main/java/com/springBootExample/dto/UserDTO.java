package com.springBootExample.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.springBootExample.model.Reminder;

public class UserDTO {

	private Long id;

	private String username;

	private String password;

	private Timestamp accountCreatedDate;

	private String isActive;

	private Set<Reminder> reminders;

	public Set<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(Set<Reminder> reminders) {
		this.reminders = reminders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountCreatedDate(Timestamp accountCreatedDate) {
		this.accountCreatedDate = accountCreatedDate;
	}

	public String getAccountCreatedDate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		return sdf.format(this.accountCreatedDate);
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
