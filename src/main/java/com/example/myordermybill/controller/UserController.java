package com.example.myordermybill.controller;

import com.example.myordermybill.entity.User;
import com.example.myordermybill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<String> getAllUsers() {
        List<User> users = userRepository.findAll();
        String userString = users.stream().map(Object::toString).collect(Collectors.joining(","));

        return new ResponseEntity<>(userString, HttpStatus.OK);
    }


    // TODO: 2021-05-18 FIX REQUEST PARAM 400 STATUS CODE
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestParam(value="username") String username,@RequestParam(value="name") String name) {
        User _user = userRepository.save(new User(username,name));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
}
