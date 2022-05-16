package com.smartcontact.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.smartcontact.entity.User;
import com.smartcontact.repository.UserRepository;
import com.smartcontact.service.UserService;

@Repository
public class UserServiceImpl implements UserService{

	@Autowired
	private BCryptPasswordEncoder passwordEcoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User saveUser(User user) {
		user.setRole("ROLE_USER");
		user.setPassword(passwordEcoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
