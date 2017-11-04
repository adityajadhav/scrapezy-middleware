package com.wildhacks.scrapit.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wildhacks.scrapit.data.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public List<User> findByName(String name);

}
