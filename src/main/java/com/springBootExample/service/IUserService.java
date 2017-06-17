package com.springBootExample.service;

import com.springBootExample.dto.UserDTO;
import com.springBootExample.model.Reminder;
import com.springBootExample.model.User;
import com.springBootExample.reminderBot.ReminderBot;

public interface IUserService {

	public void inserUser(User user);

	public UserDTO getUser(Long id) throws Throwable;

	public void addReminder() throws Throwable;

	public Reminder reminder() throws Throwable;

	public String sendResponse(ReminderBot reminderBot) throws Throwable;

}
