package io.github.yokigroup.util;

public interface WeightedPool<T> {

    /**
     * Adds an element to the pool, the weights can be an arbitrary value.
     * @param element The element to add to the pool.
     * @param weight The chance of an element to be chosen.
     */
    void addRandomizedElement(final T element, final float weight);

    /**
     *
     * @return One of the elements in the pool.
     */
    T getRandomizedElement();
}
