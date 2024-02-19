package io.github.yokigroup.util;

import java.util.Objects;

/**
 * An implementation of the binding to a vector2 class.
 */
public final class Vector2Impl implements Vector2 {
    private static final String ERROR_STRING = "The other vector was null.";
    private final org.dyn4j.geometry.Vector2 vector;
    public static final Vector2 NULL_VECTOR = new Vector2Impl(0, 0);

    public static <T extends Number> Vector2 castPair(final Pair<T, T> pair) {
        return new Vector2Impl(pair.x().doubleValue(), pair.y().doubleValue());
    }

    /**
     *
     * @param x The first component of the vector.
     * @param y The second component of the vector.
     */
    public Vector2Impl(final double x, final double y) {
        vector = new org.dyn4j.geometry.Vector2(x, y);
    }

    /**
     * Copy constructor for Vector2Impl.
     * @param vector The vector to copy.
     */
    public Vector2Impl(final Vector2 vector) {
        this(vector.getX(), vector.getY());
    }

    /**
     * Creates a Vector2 with coordinates at 0.0.
     */
    public Vector2Impl() {
        this(0.0d, 0.0d);
    }

    @Override
    public double getX() {
        return vector.x;
    }

    @Override
    public double getY() {
        return vector.y;
    }

    @Override
    public Vector2 plus(final Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException(ERROR_STRING);
        }
        return new Vector2Impl(this.getX() + other.getX(), this.getY() + other.getY());
    }

    @Override
    public Vector2 minus(final Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException(ERROR_STRING);
        }
        return new Vector2Impl(this.getX() - other.getX(), this.getY() - other.getY());
    }

    @Override
    public Vector2 times(final Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException(ERROR_STRING);
        }
        return new Vector2Impl(this.getX() * other.getX(), this.getY() * other.getY());
    }

    @Override
    public Vector2 divide(final Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException(ERROR_STRING);
        }
        return new Vector2Impl(this.getX() / other.getX(), this.getY() / other.getY());
    }

    @Override
    public Vector2 scale(final double value) {
        return new Vector2Impl(this.getX() * value, this.getY() * value);
    }

    @Override
    public Vector2 normalize() {
        final org.dyn4j.geometry.Vector2 normalized = this.vector.getNormalized();
        return new Vector2Impl(normalized.x, normalized.y);
    }

    @Override
    public double length() {
        return this.vector.getMagnitude();
    }

    @Override
    public double dot(final Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException(ERROR_STRING);
        }
        return this.vector.dot(((Vector2Impl) other).getVector());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Vector2Impl vector2 = (Vector2Impl) o;
        return Objects.equals(vector, vector2.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector);
    }

    private org.dyn4j.geometry.Vector2 getVector() {
        return this.vector;
    }
}
