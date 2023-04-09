package by.teachmeskills.penkovsky.homework17;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private final Duration timeWindow;
    private final int maxCount;
    private final Map<String, Instant[]> userToTimestamps = new HashMap<>();

    public RateLimiter(Duration timeWindow, int maxCount) {
        this.timeWindow = timeWindow;
        this.maxCount = maxCount;
    }

    public synchronized boolean tryAcquire(String user) {
        Instant[] timestamps = userToTimestamps.get(user);
        Instant now = Instant.now();
        if (timestamps == null) {
            timestamps = new Instant[maxCount];
            userToTimestamps.put(user, timestamps);
        }
        int count = 0;
        for (int i = 0; i < timestamps.length; i++) {
            Instant timestamp = timestamps[i];
            if (timestamp != null && timestamp.isAfter(now.minus(timeWindow))) {
                count++;
            }
        }
        if (count >= maxCount) {
            return false;
        }
        timestamps[count] = now;
        return true;
    }
}