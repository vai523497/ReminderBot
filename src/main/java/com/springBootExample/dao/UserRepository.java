package com.springBootExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBootExample.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findById(Long id) throws Throwable;

	public User findByUsername(String username) throws Throwable;

}
