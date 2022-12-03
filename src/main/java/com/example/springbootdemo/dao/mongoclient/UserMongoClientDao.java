package com.example.springbootdemo.dao.mongoclient;

import com.example.springbootdemo.entity.mongo.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMongoClientDao {
    //https://www.baeldung.com/queries-in-spring-data-mongodb
    @Autowired
    MongoTemplate mongoTemplate;

    public User save(User user) {
        return mongoTemplate.save(user);
    }

    public List<User> findByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.find(query, User.class);
    }

    public User findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    public void delete(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        mongoTemplate.remove(query, User.class);
    }

    public Long deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query, User.class);
        return result.getDeletedCount();
    }

    public Boolean existsById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, User.class);
    }


}
