package com.springboot.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    private Date date;

    @DBRef
    private List<JournalEntry> journalEntry = new ArrayList<>();

    private List<String> roles = new ArrayList<>();
}
