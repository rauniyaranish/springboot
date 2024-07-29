package com.springboot.service;

import com.springboot.entity.JournalEntry;
import com.springboot.entity.User;
import com.springboot.repository.JournalEntryRepository;
import com.springboot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public JournalEntry save(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    public ResponseEntity<?> deleteById(ObjectId id, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            journalEntryRepository.deleteById(id);
            user.get().getJournalEntry().removeIf(v -> v.getId().equals(id));
            userService.save(user.get(), "none");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
