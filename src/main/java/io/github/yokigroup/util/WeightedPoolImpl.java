package io.github.yokigroup.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of a pool that given elements and their weight, it returns randomized items from it.
 * @param <T> The type of objects contained in the pool.
 */
public class WeightedPoolImpl<T> implements WeightedPool<T> {
    private Set<Pair<T, Float>> itemPool;

    public WeightedPoolImpl() {
        this.itemPool = new HashSet<>();
    }

    @Override
    public WeightedPoolImpl<T> clone() {
        WeightedPoolImpl<T> clone = new WeightedPoolImpl<>();
        for (Pair<T, Float> pair : this.itemPool) {
            clone.addElement(pair.getX(), pair.getY());
        }
        return clone;
    }

    @Override
    public void addElement(final T element, final float weight) {
        if (weight <= 0.0f) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.itemPool.add(new PairImpl<>(element, weight));
    }

    @Override
    public void removeElement(final T element) {
        this.itemPool.removeIf(p -> p.getX() == element);
    }

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

    @Override
    public T removeRandomizedElement() {
        final T element = getRandomizedElement();
        removeElement(element);
        return element;
    }

    @Override
    public int size() {
        return this.itemPool.size();
    }
}
