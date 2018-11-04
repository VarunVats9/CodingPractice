package com.practice.youtube.techdummies.consistenthashing;

import java.util.SortedMap;
import java.util.concurrent.ThreadLocalRandom;

public class LoadBalancerTask implements Runnable {

    final SortedMap<Integer, String> bucketIdToServer;
    final ConsistentHashing.HashFunction hashFunction;

    public LoadBalancerTask(final SortedMap<Integer, String> bucketIdToServer, final ConsistentHashing.HashFunction hashFunction) {
        this.bucketIdToServer = bucketIdToServer;
        this.hashFunction = hashFunction;
    }

    @Override
    public void run() {

        while (true) {
            final int randomUserId = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
            final int userBucket = hashFunction.getHashValue(String.valueOf(randomUserId));

            final SortedMap<Integer,String> mapViewWithKeyGreaterThanUserBucket = bucketIdToServer.tailMap(userBucket);

            final Integer bucketIdWhichWillHandleTheUser = mapViewWithKeyGreaterThanUserBucket.isEmpty() ?
                    bucketIdToServer.firstKey() : mapViewWithKeyGreaterThanUserBucket.firstKey();

            final String serverWhichWillHandleTheUser = bucketIdToServer.get(bucketIdWhichWillHandleTheUser);

            System.out.println("--------------------------------------------------------------------------");

            System.out.println("User ID : " + randomUserId + " has been assigned to " + serverWhichWillHandleTheUser);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
