package com.practice.youtube.techdummies.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LRU {

    // Modulus for HashMap Keys, i.e. Range (0, 9)
    final static int LIMIT = 10;

    // Evict the least recently used, once the load factor is reached.
    final static double LOAD_FACTOR = 0.6;

    final static String SEPARATOR = "--------------------------------------------------------------" +
            "--------------------------------------------------------------";

    // Map which has <Super Hero Name, Reference to the Node having Power Object>
    public static final Map<String, DoublyLinkedList.Node> superHeroPower = new HashMap<>();

    // Doubly Linked List, keeping least recently queried superhero. Front of the list has least used/queried.
    public static final DoublyLinkedList leastRecentlyQueried = new DoublyLinkedList();
    private static final String SUPER_HERO = "SuperHero-";

    public static void main(String[] args) {

        Thread generateRequestsForTheServer = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(SEPARATOR);

                    // Random request to know the superhero power level.
                    final int randomRequest = ThreadLocalRandom.current().nextInt(1000) % LIMIT;
                    final String randomSuperHeroRequest = SUPER_HERO + randomRequest;
                    System.out.println("Query by the USER for : [" + randomSuperHeroRequest + "]");

                    // check if its present in the cache.
                    final boolean cacheHit = superHeroPower.containsKey(randomSuperHeroRequest);

                    if (cacheHit) {

                        // Get the answer from the cache.
                        final DoublyLinkedList.Node node = superHeroPower.get(randomSuperHeroRequest);

                        // Update the least recently queried list.
                        leastRecentlyQueried.removeNodeAndAddToLast(node);

                        // Return (print the answer)
                        System.out.println("Answer returned from CACHE, Super Hero with power level : [" +
                                node.getPower().powerLevel + "] and name : [" + node.getPower().superHeroName + "]");

                        System.out.println("Least Recently Queried Status : " + leastRecentlyQueried.print());
                        continue;
                    }

                    if (leastRecentlyQueried.size() >= LOAD_FACTOR * LIMIT) {
                        // Remove the least used entry.
                        final DoublyLinkedList.Node node = leastRecentlyQueried.removeFirst();
                        System.out.println("Evicting from CACHE the least used, Super Hero with power level : [" +
                                node.getPower().powerLevel + "] and name : [" + node.getPower().superHeroName + "]");
                        superHeroPower.remove(node.getPower().superHeroName);
                    }

                    // Assume we got the value from Db and now setting here.
                    final Power power = new Power(randomRequest, randomSuperHeroRequest);
                    final DoublyLinkedList.Node node = new DoublyLinkedList.Node(power);
                    superHeroPower.put(randomSuperHeroRequest, node);

                    // Update the least recently queried list.
                    leastRecentlyQueried.addLast(node);

                    System.out.println("Answer returned from DB, Super Hero with power level : [" +
                            power.powerLevel + "] and name : [" + power.superHeroName + "]");

                    System.out.println("Least Recently Queried Status : " + leastRecentlyQueried.print());
                }
            }
        });

        generateRequestsForTheServer.start();

        try {
            generateRequestsForTheServer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class Power {

        private int powerLevel;
        private String superHeroName;

        public Power(final int powerLevel, final String superHeroName) {
            this.powerLevel = powerLevel;
            this.superHeroName = superHeroName;
        }

        public String getSuperHeroName() {
            return this.superHeroName;
        }
    }
}
