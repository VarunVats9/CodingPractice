package com.practice.youtube.techdummies.ratelimiting.tokenbucket;

import java.time.LocalTime;
import java.util.Objects;

public class TokenEntry {

    private LocalTime localTime;
    private Integer counter;

    public TokenEntry(final LocalTime localTime, final int counter) {
        this.localTime = localTime;
        this.counter = counter;
    }

    public LocalTime getLocalTime() {
        return this.localTime;
    }

    public Integer getCounter() {
        return this.counter;
    }

    public void setCounter(final Integer counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TokenEntry that = (TokenEntry)o;
        return counter == that.counter &&
                Objects.equals(localTime, that.localTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localTime, counter);
    }
}
