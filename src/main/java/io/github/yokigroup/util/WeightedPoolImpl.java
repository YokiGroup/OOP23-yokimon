package io.github.yokigroup.util;

import java.util.Set;

public class WeightedPoolImpl<T> implements WeightedPool<T> {
    private Set<Pair<T, Float>> itemPool;

    @Override
    public void addRandomizedElement(final T element, final float weight) {

    }

    @Override
    public void removeRandomizedElement(final T element) {

    }

    @Override
    public T getRandomizedElement() {
        return null;
    }
}
