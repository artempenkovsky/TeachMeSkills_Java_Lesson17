package by.teachmeskills.penkovsky.homework17;

import java.time.Instant;
public class Message {
    private final User author;
    private final String content;
    private final Instant timestamp;

    public Message(User author, String content, Instant timestamp) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}