package com.practice.youtube.techdummies.ratelimiting.slidinglogs;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class RequestEntry {

    private LocalDateTime localDateTime;

    public RequestEntry(final LocalDateTime localDateTime) {
        localDateTime.toEpochSecond(OffsetDateTime.now().getOffset());
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

}
