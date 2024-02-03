package io.github.yokigroup.util;

/**
 * A pool that given elements and their weight, it returns randomized items from it.
 * @param <T> The type of objects contained in the pool.
 */
public interface WeightedPool<T> {

    /**
     * Adds an element to the pool, the weights can be an arbitrary value.
     * @param element The element to add to the pool.
     * @param weight The chance of an element to be chosen.
     */
    void addRandomizedElement(final T element, final float weight);

    /**
     * Removes one of the elements from the pool.
     * @param element The element to remove.
     */
    void removeRandomizedElement(final T element);

    /**
     *
     * @return One of the elements in the pool.
     */
    T getRandomizedElement();
}
