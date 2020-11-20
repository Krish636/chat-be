package com.chat.controller;

import com.chat.jwt.JwtTokenProvider;
import com.chat.model.UserDTO;
import com.chat.service.UserService;
import com.chat.storage.UserStorage;
import com.chat.utility.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@Slf4j
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        log.info("handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Integer> login(@RequestBody final UserDTO userDTO) {
        final int response = userService.findUser(userDTO);
        log.info("User login....responseCode : " + response + " !!!");
        final HttpHeaders responseHeaders = new HttpHeaders();
        if (response != 0) {
            responseHeaders.add("Authorization", JwtTokenProvider.generateToken(1001, AppConstants.VALUE_USER));
        }
        return new ResponseEntity<Integer>(response, responseHeaders, HttpStatus.OK);
    }
}
