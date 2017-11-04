package com.wildhacks.scrapit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wildhacks.scrapit.data.User;
import com.wildhacks.scrapit.repo.ScrapRepository;
import com.wildhacks.scrapit.repo.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScrapRepository scrapRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		scrapRepository.deleteAll();

		User a = new User();
		a.setName("Aditya J");

		userRepository.save(a);

		List<User> users = userRepository.findAll();
		for (User user : users) {
			System.out.println(user.getName());
		}

	}

}
