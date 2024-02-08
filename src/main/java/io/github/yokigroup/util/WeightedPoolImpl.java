package io.github.yokigroup.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of a pool that given elements and their weight, it returns randomized items from it.
 * @param <T> The type of objects contained in the pool.
 */
public class WeightedPoolImpl<T> implements WeightedPool<T> {
    private final Set<Pair<T, Float>> itemPool;

    /**
     * Creates an empty weighted pool.
     */
    public WeightedPoolImpl() {
        this.itemPool = new HashSet<>();
    }

    /**
     *
     * @return creates a copy of the pool.
     */
    @Override
    public WeightedPoolImpl<T> copy() {
        final WeightedPoolImpl<T> clone = new WeightedPoolImpl<>();
        for (final Pair<T, Float> pair : this.itemPool) {
            clone.itemPool.add(new PairImpl<>(pair.getX(), pair.getY()));
        }
        return clone;
    }

    /**
     * Adds an element to the pool, the weights can be an arbitrary value.
     * @param element The element to add to the pool.
     * @param weight The chance of an element to be chosen.
     */
    @Override
    public void addElement(final T element, final float weight) {
        if (weight <= 0.0f) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.itemPool.add(new PairImpl<>(element, weight));
    }

    /**
     * Removes one of the elements from the pool.
     * @param element The element to remove.
     */
    @Override
    public void removeElement(final T element) {
        this.itemPool.removeIf(p -> p.getX() == element);
    }

    /**
     * Gets one of the elements in the pool by their weight without removing it.
     * @return One of the elements in the pool.
     */
    @Override
    public T getRandomizedElement() {
        if (this.size() == 0) {
            throw new IllegalStateException("The randomized pool is empty.");
        }
        // Sum all the weights together
        final float sumWeight = this.itemPool.stream()
                .map(Pair::getY)
                .reduce(0.0f, Float::sum);
        // Get a randomized weight from the total
        final float randomWeight = new Random().nextFloat(sumWeight);
        float cumulativeWeight = 0.0f;
        for (final Pair<T, Float> pair : this.itemPool) {
            cumulativeWeight += pair.getY();
            if (randomWeight <= cumulativeWeight) {
                return pair.getX();
            }
        }
        throw new IllegalStateException("No element could be retrieved from the pool.");
    }

    /**
     * Gets one of the elements in the pool by their weight and removes it.
     * @return One of the elements in the pool.
     */
    @Override
    public T removeRandomizedElement() {
        final T element = getRandomizedElement();
        removeElement(element);
        return element;
    }

    /**
     *
     * @return the amount of items in the pool.
     */
    @Override
    public int size() {
        return this.itemPool.size();
    }
}
