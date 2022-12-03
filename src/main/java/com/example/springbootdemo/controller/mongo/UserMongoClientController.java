package com.example.springbootdemo.controller.mongo;

import com.example.springbootdemo.dao.mongo.UserRepository;
import com.example.springbootdemo.dao.mongoclient.UserMongoClientDao;
import com.example.springbootdemo.entity.mongo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mongo/client/user")
public class UserMongoClientController {
    @Autowired
    UserMongoClientDao userRepository;

    @PostMapping("/add")
    public User insert(User user) {
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        return userRepository.save(user);
    }

    @GetMapping("/findByName")
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/findById")
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @PutMapping("/update")
    public User update(User user) {
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        return userRepository.save(user);
    }

    @PutMapping("/updateById")
    public User updateById(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }
        if (!userRepository.existsById(user.getId())) {
            log.error("mongo db 不存在user");
            return null;
        }
        User newuser = userRepository.findById(user.getId());
        newuser.setId(user.getId());
        newuser.setUsername(user.getUsername());
        Date now = new Date();
        newuser.setUpdateTime(now);
        return userRepository.save(newuser);
    }

    @DeleteMapping("/delete")
    public void delete(User user) {
        userRepository.delete(user);
    }

    @DeleteMapping("/deleteById")
    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
