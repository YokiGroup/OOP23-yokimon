package io.github.yokigroup.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of a pool that given elements and their weight, it returns randomized items from it.
 * @param <T> The type of objects contained in the pool.
 */
public class WeightedPoolImpl<T> implements WeightedPool<T> {
    private final Set<Pair<T, Float>> itemPool;

    public WeightedPoolImpl() {
        this.itemPool = new HashSet<>();
    }

    @Override
    public void addElement(final T element, final float weight) {
        if (weight <= 0.0f) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.itemPool.add(new Pair<>(element, weight));
    }

    @Override
    public void removeElement(final T element) {
        this.itemPool.removeIf(p -> p.X() == element);
    }

    @Override
    public T getRandomizedElement() {
        if (this.size() == 0) {
            throw new IllegalStateException("The randomized pool is empty.");
        }
        // Sum all the weights together
        final float sumWeight = this.itemPool.stream()
                .map(Pair::Y)
                .reduce(0.0f, Float::sum);
        // Get a randomized weight from the total
        final float randomWeight = new Random().nextFloat(sumWeight);
        final Optional<T> element = this.itemPool.stream()
                // Calculate the closest weight to the randomized one.
                .reduce((a, b) -> Math.abs(a.Y() - randomWeight) < Math.abs(b.Y() - randomWeight) ? a : b)
                .map(Pair::X);
        if (element.isEmpty()) {
            throw new IllegalStateException("No element could be retrieved from the pool.");
        }
        return element.get();
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
