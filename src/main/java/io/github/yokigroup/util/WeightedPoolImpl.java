package io.github.yokigroup.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
     * Copy constructor for the WeightedPoolImpl.
     * @param pool The pool to copy from.
     */
    public WeightedPoolImpl(final WeightedPool<T> pool) {
        this();
        for (final Pair<T, Float> pair : pool.getItemPool()) {
            this.itemPool.add(new Pair<>(pair.x(), pair.y()));
        }
    }

    /**
     * Deep copy constructor for the WeightedPoolImpl.
     * @param pool The pool to copy from.
     */
    public WeightedPoolImpl(final WeightedPool<T> pool, final Function<T, T> copyFunction) {
        this();
        for (final Pair<T, Float> pair : pool.getItemPool()) {
            this.itemPool.add(new Pair<>(copyFunction.apply(pair.x()), pair.y()));
        }
    }

    @Override
    public final WeightedPool<T> deepCopy(final WeightedPool<T> pool, final UnaryOperator<T> copyFunction) {
        final WeightedPool<T> copyPool = new WeightedPoolImpl<>();
        for (final Pair<T, Float> pair : pool.getItemPool()) {
            copyPool.addElement(copyFunction.apply(pair.x()), pair.y());
        }
        return copyPool;
    }

    @Override
    public final void addElement(final T element, final float weight) {
        if (weight <= 0.0f) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.itemPool.add(new Pair<>(element, weight));
    }

    @Override
    public final void removeElement(final T element) {
        this.itemPool.removeIf(p -> p.x() == element);
    }

    @Override
    public final T getRandomizedElement() {
        if (this.itemPool.isEmpty()) {
            throw new IllegalStateException("The randomized pool is empty.");
        } else if (this.itemPool.size() == 1) {
            return this.itemPool.iterator().next().x();
        }
        // Sum all the weights together
        final float sumWeight = this.itemPool.stream()
                .map(Pair::y)
                .reduce(0.0f, Float::sum);
        // Get a randomized weight from the total
        final float randomWeight = new Random().nextFloat(sumWeight);
        float cumulativeWeight = 0.0f;
        for (final Pair<T, Float> pair : this.itemPool) {
            cumulativeWeight += pair.y();
            if (randomWeight <= cumulativeWeight) {
                return pair.x();
            }
        }
        throw new IllegalStateException("No element could be retrieved from the pool.");
    }

    @Override
    public final T removeRandomizedElement() {
        final T element = getRandomizedElement();
        removeElement(element);
        return element;
    }

    @Override
    public final Set<T> getEntries() {
        return Set.copyOf(
                this.itemPool.stream()
                .map(Pair::x)
                .collect(Collectors.toSet())
        );
    }

    @Override
    public final Set<Pair<T, Float>> getItemPool() {
        return Set.copyOf(itemPool);
    }

    @Override
    public final int size() {
        return this.itemPool.size();
    }
}
