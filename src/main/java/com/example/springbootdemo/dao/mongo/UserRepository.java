package com.example.springbootdemo.dao.mongo;

import com.example.springbootdemo.entity.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username:'?0'}")
    List<User> findByUsername(String username);
}
