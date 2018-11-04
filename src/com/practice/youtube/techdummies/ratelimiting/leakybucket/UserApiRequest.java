package com.practice.youtube.techdummies.ratelimiting.leakybucket;

public class UserApiRequest implements Runnable {

    private int user;

    public UserApiRequest(final int randomUser) {
        this.user = randomUser;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String userId = "User-" + user;

        System.out.println("API access given for : [" + userId + "]");
    }
}

