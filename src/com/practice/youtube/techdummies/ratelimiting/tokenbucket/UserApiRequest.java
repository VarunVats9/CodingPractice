package com.practice.youtube.techdummies.ratelimiting.tokenbucket;

import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class UserApiRequest implements Runnable {

    private ConcurrentHashMap<String, TokenEntry> userTokenCount;
    private int user;

    public UserApiRequest(final ConcurrentHashMap<String, TokenEntry> userTokenCount, final int randomUser) {
        this.userTokenCount = userTokenCount;
        this.user = randomUser;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final LocalTime nowTime = LocalTime.now();
        final String userId = "User-" + user;

        final TokenEntry entry = userTokenCount.getOrDefault(userId, null);

        if (Objects.isNull(entry) || entry.getLocalTime().getHour() < nowTime.getHour()
                || entry.getLocalTime().getHour() > nowTime.getHour() || entry.getLocalTime().getMinute() < nowTime.getMinute()) {
            userTokenCount.put(userId, new TokenEntry(nowTime, 1));
            System.out.println("API token counter for : [" + userId + "] set to : [1]");
            return;
        }

        if (entry.getCounter() >= TokenBucket.API_LIMIT_FOR_EACH_USER) {
            System.out.println("Blocked the current request from : [" + userId + "]");
        }

        entry.setCounter(entry.getCounter() + 1);

        System.out.println("API token counter for : [" + userId + "] set to : [" + entry.getCounter() + "]");
    }
}
