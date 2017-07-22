package com.springBootExample.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ResponceGenerator.SpecifiedMSGWebhookReponse;
import com.springBootExample.dao.ReminderRepository;
import com.springBootExample.dao.UserRepository;
import com.springBootExample.dto.UserDTO;
import com.springBootExample.model.Reminder;
import com.springBootExample.model.User;
import com.springBootExample.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReminderRepository reminderRepository;

	@Override
	public void inserUser(User user) {
		reminderRepository.getReminderByReminderTime("29-04-2017 14:05");
		this.userRepository.save(user);

	}

	@Override
	public UserDTO getUser(Long id) throws Throwable {
		/* User user = this.userRepository.findByUsername("vaibhav"); */
		User user = this.userRepository.findById(id);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setAccountCreatedDate(user.getUserCreatedDate());
		userDTO.setReminders(user.getReminderSets());
		return userDTO;
	}

	public void addReminder() throws Throwable {
		User user = this.userRepository.findAll().get(1);
		Reminder reminder = new Reminder();
		reminder.setReminderName("name");
		reminder.setUser(user);
		String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
		reminder.setReminderTime(date);

		this.reminderRepository.save(reminder);
		Reminder reminder1 = new Reminder();
		reminder1.setReminderName("name");
		reminder1.setUser(user);
		String date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
		reminder.setReminderTime(date1);
		this.reminderRepository.save(reminder1);

	}

	public Reminder reminder() throws Throwable {
		Reminder reminder = this.reminderRepository.findAll().get(0);
		return reminder;
	}

	@Override
	public String sendResponse(String reminderBot) throws Throwable {

		SpecifiedMSGWebhookReponse test = new SpecifiedMSGWebhookReponse();
		test.setRequestUrl("https://graph.facebook.com/v2.6/me/messages?access_token=",
				"EAAbTgBtVwEgBAKZCsDgvdjgQX4to8gGOqQyjZAC9cgfU5nIbpVFqV4CpOsxnDWh3StOOPdwci9yVHIWIUn2WBsM4eHhzBBLGpUsD0ti1y2GIZACkXSBvif369DywfZCIZANkQqagxLzNXbCabePgS8zjYlmZAp6LZBjMcjw75nCmwZDZD");
		System.out.println(test.getUserMesssage(reminderBot));
		test.responseMSG();

		return "";

	}

}
