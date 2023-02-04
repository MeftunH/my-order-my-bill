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
    public ResponseEntity<String> getAllUsers() {
        List<User> users = userRepository.findAll();
        String userString = users.stream().map(Object::toString).collect(Collectors.joining(","));

        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserSave userSave) {
        //create date now
        Date date = Calendar.getInstance().getTime();
        User _user = userRepository.save(new User(userSave.getUsername(),userSave.getName(),date));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @GetMapping("/listWithLetterThem")
    public ResponseEntity<String> getUsersWithLetterThem() {
        List<User> users = userRepository.findAll();
        String userString = users.stream().filter(i -> i.getUsername().toUpperCase().contains("C"))
                .map(Object::toString).collect((Collectors.joining(",")));

        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    @GetMapping("/totalAmountOfInvoicesInJune")
    public ResponseEntity<String> totalAmountOfInvoicesInJune() {

        List<User> users = userRepository.getUsersCreatedInJune();

        String userString =users.stream().flatMap(c -> billRepository.findBillsByUser(c).stream())
                .map(Object::toString)
                .collect(Collectors.toList()).toString();

        return new ResponseEntity<>(userString, HttpStatus.OK);
    }
}
