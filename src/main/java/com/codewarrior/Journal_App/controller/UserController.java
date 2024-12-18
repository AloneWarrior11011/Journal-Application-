package com.codewarrior.Journal_App.controller;

import com.codewarrior.Journal_App.entity.JournalEntry;
import com.codewarrior.Journal_App.service.JournalEntryService;
import com.codewarrior.Journal_App.service.UserService;
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

 }
