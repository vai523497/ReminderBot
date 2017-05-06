/**
 * 
 */
package com.springBootExample.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.springBootExample.model.User;

/**
 * @author VAIBHAV-PC
 *
 */
public class ReminderDTO {

	private int id;

	private String reminderName;

	private Timestamp reminderTime;

	private Timestamp reminderCreatedDate;

	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReminderName() {
		return reminderName;
	}

	public void setReminderName(String reminderName) {
		this.reminderName = reminderName;
	}

	public Timestamp getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(Timestamp reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getReminderCreatedDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		return sdf.format(this.reminderCreatedDate);
	}

	public void setReminderCreatedDate(Timestamp reminderCreatedDate) {
		this.reminderCreatedDate = reminderCreatedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
