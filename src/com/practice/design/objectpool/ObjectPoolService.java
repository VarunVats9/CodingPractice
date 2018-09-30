package com.practice.design.objectpool;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ObjectPoolService<T> implements ObjectPool<T> {

    private static final int NONE_ARE_LEFT = 0;
    private final int minIdle;
    private final int maxIdle;
    private final int DEFAULT_VALUE = 0;

    // (2 minutes) If all the objects are not released, will shutdown in this expiry interval;
    private final long EXPIRY_INTERVAL_FOR_POOL_TO_SHUTDOWN = 2 * 60 * 1000;


    private final ArrayBlockingQueue<T> pool;
    private final Lock lock = new ReentrantLock(true);
    private final Condition objectsReachedMaxIdleLimit = lock.newCondition();

    private AtomicInteger objectsCreated;
    private AtomicInteger borrowedObjects;

    public ObjectPoolService(final int minIdle, final int maxIdle) {
        this.minIdle = minIdle;
        this.maxIdle = maxIdle;
        this.pool = new ArrayBlockingQueue<T>(maxIdle, true);
    }

    public int minIdle() {
        return this.minIdle;
    }

    public int maxIdle() {
        return this.maxIdle;
    }

    public long expiryInterval() {
        return this.EXPIRY_INTERVAL_FOR_POOL_TO_SHUTDOWN;
    }

    public int created() {
        return this.objectsCreated.get();
    }

    public int borrowed() {
        return this.borrowedObjects.get();
    }

    public int availableActive() {
        lock.lock();
        try {
            return this.objectsCreated.get() - this.borrowedObjects.get();
        } finally {
            lock.unlock();
        }
    }

    public int availablePassive() {
        lock.lock();
        try {
            return this.maxIdle - this.objectsCreated.get();
        } finally {
            lock.unlock();
        }
    }

    public int availableTotal() {
        lock.lock();
        try {
            return this.maxIdle - this.borrowedObjects.get();
        } finally {
            lock.unlock();
        }
    }

    public T borrowObject() {
        lock.lock();
        try {

            T object = null;
            // All the created objects have been borrowed, and it has reached maxIdle limit.
            if (this.availableTotal() == NONE_ARE_LEFT) {
                return object;
            }

            // Some objects are available in the pool.
            if (this.availableActive() > 0) {
                try {
                    object = pool.take();

                    // Object borrowed count decreased.
                    this.borrowedObjects.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " borrowObject() " + "Borrowed : " + this.borrowedObjects.get() + " Created : " + this.objectsCreated.get());
                    return object;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // No objects are left in the pool, but the maxIdle limit has not been reached, create one.
            if (this.objectsCreated.get() < this.maxIdle) {
                object = this.createObject();

                // Object created count increased.
                this.objectsCreated.incrementAndGet();
                // Object borrowed count increased.
                this.borrowedObjects.incrementAndGet();
            }

            // Returning.
            System.out.println(Thread.currentThread().getName() + " borrowObject() " + "Borrowed : " + this.borrowedObjects.get() + " Created : " + this.objectsCreated.get());
            return object;
        } finally {
            lock.unlock();
        }
    }

    public T tryBorrowObject(long timeout) {
        lock.lock();
        try {

            T object = null;
            boolean lockAcquired = true;
            // All the created objects have been borrowed, and it has reached maxIdle limit.
            if (this.availableTotal() == NONE_ARE_LEFT) {
                try {
                    lockAcquired = objectsReachedMaxIdleLimit.await(timeout, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    return object;
                }
            }

            // Lock cannot be acquired in the given timeout.
            if (!lockAcquired) {
                return object;
            }

            // Some objects are available in the pool.
            if (this.availableActive() > 0) {
                try {
                    object = pool.take();

                    // Object borrowed count decreased.
                    this.borrowedObjects.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " borrowObject() " + "Borrowed : " + this.borrowedObjects.get() + " Created : " + this.objectsCreated.get());
                    return object;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // No objects are left in the pool, but the maxIdle limit has not been reached, create one.
            if (this.objectsCreated.get() < this.maxIdle) {
                object = this.createObject();

                // Object created count increased.
                this.objectsCreated.incrementAndGet();
                // Object borrowed count increased.
                this.borrowedObjects.incrementAndGet();
            }

            // Returning.
            System.out.println(Thread.currentThread().getName() + " tryBorrowObject() " + "Borrowed : " + this.borrowedObjects.get() + " Created : " + this.objectsCreated.get());
            return object;
        } finally {
            lock.unlock();
        }
    }

    public void releaseObject(T object) {
        lock.lock();
        try {

            if (Objects.isNull(object)) {
                return;
            }

            // Object has been added, hence signalling all the awaited threads.
            this.pool.add(object);
            objectsReachedMaxIdleLimit.signal();

            // Object released.
            this.borrowedObjects.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " releaseObject() " + "Borrowed : " + this.borrowedObjects.get() + " Created : " + this.objectsCreated.get());
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            while (this.availableActive() == this.created()) {
                try {
                    lock.wait(EXPIRY_INTERVAL_FOR_POOL_TO_SHUTDOWN);
                } catch (InterruptedException e) {
                    this.pool.clear();
                }
            }
            this.pool.clear();
        } finally {
            lock.unlock();
        }
    }

    protected abstract T createObject();

    public void initialize(final int minIdle, final int maxIdle) {
        this.objectsCreated = new AtomicInteger(DEFAULT_VALUE);
        this.borrowedObjects = new AtomicInteger(DEFAULT_VALUE);

        // Create minimum objects, and put it in the object pool.
        for (int i = 0; i < minIdle; i++) {
            final T object = this.createObject();
            this.pool.add(object);

            // Object created count increased.
            this.objectsCreated.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + " initialize() " + "Borrowed : " + this.borrowedObjects.get() + " Created : " + this.objectsCreated.get());
    }
}
