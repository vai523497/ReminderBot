package com.springBootExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBootExample.model.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
	
	public Reminder getReminderByReminderTime(String reminderTime);

}
