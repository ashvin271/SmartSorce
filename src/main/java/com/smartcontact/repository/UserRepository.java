package com.smartcontact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartcontact.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.userName=?1")
	public User getUserByUserName(String email);
}
