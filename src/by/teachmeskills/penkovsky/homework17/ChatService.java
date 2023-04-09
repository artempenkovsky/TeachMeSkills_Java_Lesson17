package by.teachmeskills.penkovsky.homework17;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    private final RateLimiter rateLimiter;
    private final List<Message> messages = new ArrayList<>();

    public ChatService(RateLimitingProperties rateLimitingProperties) {
        this.rateLimiter = new RateLimiter(rateLimitingProperties.getTimeWindow(), rateLimitingProperties.getMaxCount());
    }

    public boolean addMessage(User author, String content) {
        if (author == null || content == null) {
            return false;
        }
        if (rateLimiter.tryAcquire(author.getLogin())) {
            Message message = new Message(author, content, Instant.now());
            messages.add(message);
            return true;
        } else {
            return false;
        }
    }

    public boolean sendMessage(User author, String content) {
        return addMessage(author, content);
    }

    public Message[] getAll() {
        return messages.toArray(new Message[0]);
    }
}