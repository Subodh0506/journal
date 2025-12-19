package com.example.journal.controller;

import java.util.List;

import com.example.journal.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import com.example.journal.service.JournalService;

import com.example.journal.entity.JournalEntity;

@RestController
@AllArgsConstructor
@RequestMapping("/journals")
public class JournalController {

    private final JournalService journalService;
    
    @PostMapping// create journal entry
    public ResponseEntity<UserEntity> createJournal(@RequestBody JournalEntity journalEntity) {
        UserEntity result = journalService.createJournal(journalEntity);
        if(result == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}") // get journal by id
    public ResponseEntity<JournalEntity> getById(@PathVariable ObjectId id) {
        JournalEntity journal = journalService.getJournalById(id);
        if(journal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @GetMapping // get all journals
    public ResponseEntity<List<JournalEntity>> getAllJournals() {
        List<JournalEntity> result = journalService.getAllJournals();
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<JournalEntity> updateJournal(@RequestBody JournalEntity journalEntity) {
        JournalEntity result = journalService.updateJournal(journalEntity);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable ObjectId id) {
        String result = journalService.deleteJournal(id);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllJournals() {
        return new ResponseEntity<>(journalService.deleteAllJournals(), HttpStatus.ACCEPTED);
    }
}
