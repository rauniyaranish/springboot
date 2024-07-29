package com.springboot.repository;

import com.springboot.entity.JournalEntry;
import com.springboot.entity.JournalEntryV2;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
