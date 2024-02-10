package io.github.yokigroup.util;

import java.util.AbstractMap.SimpleEntry;

/**
 * Implementation of the Pair interface binding.
 * @param <T> the type of the first element
 * @param <K> the type of the second element
 */
public class PairImpl<T, K> implements Pair<T, K> {
    private final SimpleEntry<T, K> pair;

    /**
     * Creates a pair with two elements.
     * @param x The first element.
     * @param y The second element.
     */
    public PairImpl(final T x, final K y) {
        this.pair = new SimpleEntry<>(x, y);
    }

    @Override
    public final T getX() {
        return this.pair.getKey();
    }

    @Override
    public final K getY() {
        return this.pair.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        final PairImpl<?, ?> oPair = (PairImpl<?, ?>) o;
        return this.getX() == oPair.getX() && this.getY() == oPair.getY();
    }

    @Override
    public int hashCode() {
        return this.pair.hashCode();
    }
}
