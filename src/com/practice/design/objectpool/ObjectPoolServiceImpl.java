package com.practice.design.objectpool;

import java.util.Random;

public class ObjectPoolServiceImpl extends ObjectPoolService<Integer> {

    public ObjectPoolServiceImpl(final int minIdle, final int maxIdle) {
        super(minIdle, maxIdle);
        super.initialize(minIdle, maxIdle);
    }

    @Override
    protected Integer createObject() {
        return new Random().nextInt();
    }
}
