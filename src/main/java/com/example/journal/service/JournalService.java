package com.example.journal.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.journal.entity.UserEntity;
import com.example.journal.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.journal.entity.JournalEntity;
import com.example.journal.repository.JournalRepo;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JournalService {

    private final JournalRepo journalRepo;

    private final UserRepo userRepo;

    public UserEntity createJournal(JournalEntity journalEntity, String userName) {
        UserEntity check = userRepo.findByUserName(userName);
        if(check == null)
            return null;
        journalEntity.setDate(LocalDateTime.now());
        List<JournalEntity> appends = check.getJournalEntries();
        appends.add(journalEntity);
        check.setJournalEntries(appends);
        journalRepo.save(journalEntity);
        return userRepo.save(check);
    }

    public JournalEntity getJournalById(ObjectId id) {
        return journalRepo.findById(id).orElse(null);
    }

    public List<JournalEntity> getAllJournals() {
        return journalRepo.findAll();
    }

    @Transactional
    public JournalEntity updateJournal(JournalEntity journalEntity) {
        if(journalEntity.getId() == null)
            return null;
        JournalEntity existingJournal = journalRepo.findById(journalEntity.getId()).orElse(null);
        if(existingJournal == null)
            return null;
        existingJournal.setTitle(journalEntity.getTitle());
        existingJournal.setContent(journalEntity.getContent());
        return journalRepo.save(existingJournal);
    }

    @Transactional
    public String deleteJournal(ObjectId id) {
        JournalEntity journalEntity = journalRepo.findById(id).orElse(null);
        if(journalEntity == null)
            return "journal not exists";
        journalRepo.deleteById(id);
        return "journal deleted";
    }

}
