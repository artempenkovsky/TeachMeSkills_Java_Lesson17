package by.teachmeskills.penkovsky.homework17;

import java.time.Duration;

public class RateLimitingProperties {
    private final Duration timeWindow;
    private final int maxCount;

    public RateLimitingProperties(Duration timeWindow, int maxCount) {
        this.timeWindow = timeWindow;
        this.maxCount = maxCount;
    }

    public Duration getTimeWindow() {
        return timeWindow;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
