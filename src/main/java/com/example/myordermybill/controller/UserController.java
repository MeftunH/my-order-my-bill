package com.example.myordermybill.controller;

import com.example.myordermybill.entity.User;
import com.example.myordermybill.repository.BillRepository;
import com.example.myordermybill.repository.UserRepository;
import com.example.myordermybill.request.UserSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BillRepository billRepository;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<User> userList = users.stream().toList();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserSave userSave) {
        //create date now
        Date date = Calendar.getInstance().getTime();
        User _user = userRepository.save(new User(userSave.getUsername(),userSave.getName(),date));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @GetMapping("/listWithLetterThem")
    public ResponseEntity<List<User>> getUsersWithLetterThem() {
        List<User> users = userRepository.findAll();
        List<User> userList = users.stream().filter(i -> i.getUsername().toUpperCase().contains("C"))
                .toList();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
