package com.codewarrior.Journal_App.service;

import com.codewarrior.Journal_App.entity.JournalEntry;
import com.codewarrior.Journal_App.entity.User;
import com.codewarrior.Journal_App.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component // now spring this JournalEntryService Component  in IOC
@Slf4j
public class JournalEntryService {
    // to use journalEntryRepository interface stuff we have to use dependency injection
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            log.error("Exception : ", e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}

// controller call ---> service call  ---> repository
