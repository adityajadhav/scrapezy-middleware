package com.wildhacks.scrapit.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wildhacks.scrapit.data.ScrapManger;

@Repository
public interface ScrapRepository extends MongoRepository<ScrapManger, String> {

	public List<ScrapManger> findByName(String name);

}
