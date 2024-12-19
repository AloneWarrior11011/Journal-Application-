package com.codewarrior.Journal_App.controller;

import com.codewarrior.Journal_App.entity.JournalEntry;
import com.codewarrior.Journal_App.entity.User;
import com.codewarrior.Journal_App.service.JournalEntryService;
import com.codewarrior.Journal_App.service.UserService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    // UpdateUser functionaliy is remaining and this giving
   @PutMapping("/{username}")
   public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
        User userInDb = userService.findByUserName(username);
        if(userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword((user.getPassword()));
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
