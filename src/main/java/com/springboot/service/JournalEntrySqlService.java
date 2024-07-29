package com.springboot.service;

import com.springboot.entity.JournalEntryV2;
import com.springboot.repository.JournalEntrySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntrySqlService {

    @Autowired
    private JournalEntrySqlRepository journalEntrySqlRepository;

    public void save(JournalEntryV2 journalEntryV2) {
        journalEntrySqlRepository.save(journalEntryV2);
    }
}
