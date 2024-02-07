package io.github.yokigroup.util;

/**
 * Implementation of the Pair interface binding.
 * @param <T> the type of the first element
 * @param <K> the type of the second element
 */
public class PairImpl<T, K> implements Pair<T, K> {
    private final javafx.util.Pair<T, K> pair;

    /**
     * Creates a pair with two elements.
     * @param x The first element.
     * @param y The second element.
     */
    public PairImpl(final T x, final K y) {
        this.pair = new javafx.util.Pair<>(x, y);
    }

    /**
     *
     * @return the first element in the pair.
     */
    @Override
    public T getX() {
        return this.pair.getKey();
    }

    /**
     *
     * @return the second element in the pair.
     */
    @Override
    public K getY() {
        return this.pair.getValue();
    }
}
