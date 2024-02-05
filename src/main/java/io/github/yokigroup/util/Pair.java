package io.github.yokigroup.util;

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
