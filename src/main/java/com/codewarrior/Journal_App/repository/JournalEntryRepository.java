package com.codewarrior.Journal_App.repository;

import com.codewarrior.Journal_App.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {
}
