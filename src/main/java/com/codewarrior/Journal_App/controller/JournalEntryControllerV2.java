package com.codewarrior.Journal_App.controller;

import com.codewarrior.Journal_App.entity.JournalEntry;
import com.codewarrior.Journal_App.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// controller is like special type of components that handles http request
@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    // spring have make instance of journalEntryService we are injecting here - DI
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping                   // goes on the localhost:8080/journal
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    // when data is sent from the postman (PostMapping)
    // It's like saying hey spring take the data from the request and convert into
    // java object so that I can use in my java code
    // here instance of JournalEntry (myEntry) created
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
       try{
          // myEntry.setDate(LocalDateTime.now());
           journalEntryService.saveEntry(myEntry);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
       }
       catch(Exception e){
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
    }

    // If we wanted see the data by specific id then we have to do the following
    // endpoint for getting data of journal entry by id
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId){
        // WE ARE USING REPSONSEENTITY CLASS OVER THERE SO THAT CLIENT WOULD KNOW HTTP RESPONSE AT THEIR SIDE.
       Optional<JournalEntry> journalEntry =  journalEntryService.getById(myId);
       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){ // ? means wild card pattern
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // now if wanna update it by id then
    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.getById(id).orElse(null);

        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(newEntry, HttpStatus.OK);
        }
//        journalEntryService.saveEntry(old);
//        return old;
        // if that entry isn't in the db
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 }
