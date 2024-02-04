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
    public void addElement(final T element, final float weight) {
        this.itemPool.add(new Pair<>(element, weight));
    }

    @Override
    public void removeElement(final T element) {
        this.itemPool.removeIf(p -> p.X() == element);
    }

    @Override
    public T getRandomizedElement() {
        final float sumWeight = this.itemPool.stream()
                .map(Pair::Y)
                .reduce(0.0f, Float::sum);
        float randomWeight = new Random().nextFloat(sumWeight);
        return this.itemPool.stream()
                .reduce((a, b) -> a.Y() > randomWeight ? a : b)
                .map(Pair::X)
                .get();
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
