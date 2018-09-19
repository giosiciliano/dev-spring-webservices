package com.gio.learning.jpa.jpain10steps.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.gio.learning.jpa.jpain10steps.entity.User;

@Repository // interacts with the DB
@Transactional // ensures each method is a transaction to the DB
public class UserDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;

	public long insert(User user) {
		entityManager.persist(user); // tracks user changes
		return user.getId();
	}
	
	/*
	 * Spring Data jpa (UserRepository)
	 * 
	 * provides DAO implementations so you dont have to code the common save, find, delete etc
	 */

}
