package com.wildhacks.scrapit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wildhacks.scrapit.data.User;
import com.wildhacks.scrapit.repo.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public final List<User> getUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}



}
