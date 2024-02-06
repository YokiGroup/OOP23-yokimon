package io.github.yokigroup.util;

/**
 * A data structure containing two elements.
 * @param <T> The type of the first element.
 * @param <K> The type of the second element.
 */
public interface Pair<T, K> {
    /**
     *
     * @return the first component of the pair
     */
    T getX();

    /**
     *
     * @return the second component of the pair.
     */
    K getY();
}
