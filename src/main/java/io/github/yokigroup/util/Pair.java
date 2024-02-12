package io.github.yokigroup.util;

/**
 * An immutable record containing two components.
 * @param x The first component of the pair.
 * @param y The second component of the pair.
 * @param <T> The type of the first component.
 * @param <K> The type of the second component.
 */
public record Pair<T, K>(T x, K y) { }
