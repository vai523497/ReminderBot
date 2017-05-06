package com.springBootExample.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "T_USR_MASTER", schema = "sa", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
		"username" }))
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column(name = "isActive")
	private String isActive;

	@Column(name = "user_Created_date")
	@CreationTimestamp
	private Timestamp UserCreatedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Reminder> reminderSets;

	public Set<Reminder> getReminderSets() {
		return reminderSets;
	}

	public void setReminderSets(Set<Reminder> reminderSets) {
		this.reminderSets = reminderSets;
	}

	public Timestamp getUserCreatedDate() {
		return UserCreatedDate;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public User() {

		// TODO Auto-generated constructor stub
	}

}
