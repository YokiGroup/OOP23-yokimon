package io.github.yokigroup.util;

/**
 * A pool that given elements and their weight, it returns randomized items from it.
 * @param <T> The type of objects contained in the pool.
 */
public interface WeightedPool<T> {

    /**
     *
     * @return creates a copy of the pool.
     */
    WeightedPool<T> clone();

    /**
     * Adds an element to the pool, the weights can be an arbitrary value.
     * @param element The element to add to the pool.
     * @param weight The chance of an element to be chosen.
     */
    void addElement(T element, float weight);

    /**
     * Removes one of the elements from the pool.
     * @param element The element to remove.
     */
    void removeElement(T element);

    /**
     * Gets one of the elements in the pool by their weight without removing it.
     * @return One of the elements in the pool.
     */
    T getRandomizedElement();

    /**
     * Gets one of the elements in the pool by their weight and removes it.
     * @return One of the elements in the pool.
     */
    T removeRandomizedElement();

    /**
     *
     * @return the amount of items in the pool.
     */
    int size();
}
