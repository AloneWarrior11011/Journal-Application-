package com.codewarrior.Journal_App.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

// as mongodb has collections and it has document(as row)
// now entire stuff is mapped with collection = journal_entry
@Document(collection = "journal_entries")
// using lomhok we just need to give annotations for the require stuff
//@Getter
//@Setter
// we don't need even write specific annotations @Data annotation will do it by own at complile time
@Data
@NoArgsConstructor // requires when convetions between jsong to pojo (as following)
public class JournalEntry {
    // now Id will map to Id in the collection in db
    @Id
    public ObjectId id;
    @NonNull
    public String title;
    public String content;

    private LocalDateTime date;
// Using LOMHOK java environment based library we can get rid of such following codes juse put dependency in .xml file
//    public LocalDateTime getData() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
}
