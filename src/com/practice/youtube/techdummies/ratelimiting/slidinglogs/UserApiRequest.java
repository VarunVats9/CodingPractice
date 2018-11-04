package com.practice.youtube.techdummies.ratelimiting.slidinglogs;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class UserApiRequest implements Runnable {

    private static final ZoneOffset ZONE_OFFSET = OffsetDateTime.now().getOffset();

    private ConcurrentHashMap<String, Deque<RequestEntry>> slidingLogs;

    private int user;

    public UserApiRequest(final ConcurrentHashMap<String, Deque<RequestEntry>> slidingLogs, final int randomUser) {
        this.slidingLogs = slidingLogs;
        this.user = randomUser;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String userId = "User-" + user;
        final RequestEntry entry = new RequestEntry(LocalDateTime.now());

        final Deque<RequestEntry> userRequestQueue = slidingLogs.getOrDefault(userId, null);

        if (Objects.isNull(userRequestQueue)) {
            final Deque<RequestEntry> deque = new LinkedBlockingDeque<>(SlidingLogs.API_LIMIT_FOR_EACH_USER);
            deque.add(entry);
            slidingLogs.put(userId, deque);
            System.out.println("Setting the cache for : [" + userId + "]");
            return;
        }

        final long currentRequestTime = entry.getLocalDateTime().toEpochSecond(ZONE_OFFSET);

        if (userRequestQueue.size() == SlidingLogs.API_LIMIT_FOR_EACH_USER &&
                currentRequestTime - userRequestQueue.peekLast().getLocalDateTime().toEpochSecond(ZONE_OFFSET)
                        < SlidingLogs.FOUR_SECONDS) {
            System.out.println("Current Second : [" + LocalTime.now().getSecond() + "] " +
                    " User has reached the API limit, BLOCKING the current request from : [" + userId + "]");
            return;
        }

        while (!userRequestQueue.isEmpty() &&
                currentRequestTime - userRequestQueue.peekLast().getLocalDateTime().toEpochSecond(ZONE_OFFSET)
                        > SlidingLogs.FOUR_SECONDS) {
            userRequestQueue.removeLast();
        }

        userRequestQueue.offerFirst(entry);
        System.out.println("Current Second : [" + LocalTime.now().getSecond() + "] " +
                " User has not requested in last four seconds, LETTING the current API accessed by : [" + userId + "]");
    }
}
