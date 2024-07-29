package com.springboot.repository;

import com.springboot.entity.JournalEntryV2;
import org.springframework.data.repository.CrudRepository;

public interface JournalEntrySqlRepository extends CrudRepository<JournalEntryV2, Long> {
}
