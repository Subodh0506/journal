package com.example.journal.entity;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;


@Document(collection = "journals")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
