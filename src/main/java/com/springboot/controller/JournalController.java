package com.springboot.controller;

import com.springboot.entity.JournalEntry;
import com.springboot.entity.User;
import com.springboot.exception.ResponseNotFound;
import com.springboot.service.JournalEntryService;
import com.springboot.service.JournalEntrySqlService;
import com.springboot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {

	@Autowired
	private JournalEntryService journalEntryService;

	@Autowired
	private JournalEntrySqlService journalEntrySqlService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getJournalEntryByUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			List<JournalEntry> journalEntry = user.get().getJournalEntry();
			return new ResponseEntity<>(journalEntry, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseNotFound(), HttpStatus.NOT_FOUND);
	}

	/*@PostMapping
	public void setJournalEntry(@RequestBody JournalEntry entry) {
		entry.setDate(new Date());
		journalEntryService.save(entry);
		JournalEntryV2 journalEntryV2 = new JournalEntryV2();
		journalEntryV2.setTitle(entry.getTitle());
		journalEntryV2.setContent(entry.getContent());
		journalEntryV2.setDate(entry.getDate());
		journalEntrySqlService.save(journalEntryV2);
	}*/
	@PostMapping("/create-journal")
	@Transactional
	public ResponseEntity<?> setJournalEntryToUser(@RequestBody JournalEntry entry) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			entry.setDate(new Date());
			JournalEntry saved = journalEntryService.save(entry);
			user.get().getJournalEntry().add(saved);
			userService.save(user.get(), "old");
			return new ResponseEntity<>(saved, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseNotFound(), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> user = userService.findByUsername(username);
		Optional<JournalEntry> foundJournal = user.get().getJournalEntry().stream().filter(x -> x.getId().equals(id)).findFirst();
		System.out.println("foundJournal =>> " + foundJournal);
		if (foundJournal.isPresent()) {
			return new ResponseEntity<>(foundJournal.get(), HttpStatus.OK);
		} else {
			ResponseNotFound notFound = new ResponseNotFound();
			return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> user = userService.findByUsername(username);
		Optional<JournalEntry> foundJournal = user.get().getJournalEntry().stream().filter(x -> x.getId().equals(id)).findFirst();
		System.out.println("foundJournal =>> " + foundJournal);
		if (foundJournal.isPresent()) {
			journalEntryService.deleteById(id, username);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			ResponseNotFound notFound = new ResponseNotFound();
			return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Optional<User> user = userService.findByUsername(username);
		Optional<JournalEntry> foundJournal = user.get().getJournalEntry().stream().filter(x -> x.getId().equals(id)).findFirst();
		if (foundJournal.isPresent()) {
			entry.setId(id);
			journalEntryService.save(entry);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		ResponseNotFound notFound = new ResponseNotFound();
		return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
	}
}