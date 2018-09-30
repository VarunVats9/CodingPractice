package com.practice.design.objectpool;

/**
 * Defines the object pool operations.
 */
public interface ObjectPool<T>
{
    /**
     * Returns the minimum number of idle objects in the pool at construction time.
     * This parameter never changes. This corresponds to initial size of pool.
     *
     * @return minimum number of idle objects in pool
     */
    public int minIdle();

    /**
     * Returns the maximum number of idle objects in the pool at construction time.
     * This parameter never changes. This corresponds to max size of pool.
     *
     * @return maximum number of idle objects in pool
     */
    public int maxIdle();

    /**
     * Returns the total number of created objects for the object pool.
     *
     * @return the total number of created objects for this object pool
     */
    public int created();

    /**
     * Returns the number of objects borrowed from this object pool.
     * This number is less than or equal to the {@link #maxIdle()} object pool
     *
     * @return the number of objects borrowed from this object pool
     */
    public int borrowed();

    /**
     * Returns the total number of created objects present in the pool currently.
     * This number is equal to {@link #created()} - {@link #borrowed()}
     *
     * @return the total number of created objects for this object pool
     */
    public int availableActive();

    /**
     * Returns the total number of objects that can be created on need basis.
     * This number is equal to {@link #created()} - {@link #maxIdle()}
     *
     * @return the total number of created objects for this object pool
     */
    public int availablePassive();

    /**
     * Returns the number of objects that can be borrowed from the pool currently.
     * This number is equal to {@link #maxIdle()} - {@link #borrowed()}
     *
     * @return the total number of created objects for this object pool
     */
    public int availableTotal();

    /**
     * Returns the valid object from the pool if available.
     * Returns null in case {@link #maxIdle()} objects have already been borrowed
     * This number is equal to {@link #maxIdle()} - {@link #borrowed()}
     *
     * @return Object from pool
     */
    T borrowObject();

    /**
     * Returns the valid object from the pool if available.
     * Waits for the specified timeout period in case on non-availability of Object
     * in Pool.
     * Returns null in case of non-availability of Object in pool even after specified
     * wait timeout.
     *
     * @return Object from pool
     */
    T tryBorrowObject(long timeout);

    /**
     * Releases the borrowed object to the pool.
     */
    void releaseObject(T object);

    /**
     * Initializes the pool with minimum and maximum objects config
     */
    void initialize(final int minIdle, final int maxIdle);

    /**
     * Terminates the pool and releases all the objects
     */
    public void shutdown();

}