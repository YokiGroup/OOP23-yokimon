package io.github.yokigroup.util;

/**
 * An implementation of the binding to a vector2 class.
 */
public class Vector2Impl implements Vector2 {
    private final org.dyn4j.geometry.Vector2 vector;

    /**
     *
     * @param x The first component of the vector.
     * @param y The second component of the vector.
     */
    public Vector2Impl(final double x, final double y) {
        vector = new org.dyn4j.geometry.Vector2(x, y);
    }

    /**
     * Creates a Vector2 with coordinates at 0.0.
     */
    public Vector2Impl() {
        this(0.0d, 0.0d);
    }

    /**
     *
     * @return the first component of the vector
     */
    @Override
    public double getX() {
        return vector.x;
    }

    /**
     *
     * @return the second component of the vector.
     */
    @Override
    public double getY() {
        return vector.y;
    }

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the addition between this and another vector (component per component).
     */
    @Override
    public Vector2 plus(final Vector2 other) {
        return new Vector2Impl(this.getX() + other.getX(), this.getY() + other.getY());
    }

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the subtraction between this and another vector (component per component).
     */
    @Override
    public Vector2 minus(final Vector2 other) {
        return new Vector2Impl(this.getX() - other.getX(), this.getY() - other.getY());
    }

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the multiplication between this and another vector (component per component).
     */
    @Override
    public Vector2 times(final Vector2 other) {
        return new Vector2Impl(this.getX() * other.getX(), this.getY() * other.getY());
    }

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the division between this and another vector (component per component).
     */
    @Override
    public Vector2 divide(final Vector2 other) {
        return new Vector2Impl(this.getX() / other.getX(), this.getY() / other.getY());
    }

    /**
     *
     * @param value The value to scale the vector by.
     * @return a new vector scaled by that value.
     */
    @Override
    public Vector2 scale(final double value) {
        return new Vector2Impl(this.getX() * value, this.getY() * value);
    }

    /**
     *
     * @return a new normalized vector (with length 1).
     */
    @Override
    public Vector2 normalize() {
        final org.dyn4j.geometry.Vector2 normalized = this.vector.getNormalized();
        return new Vector2Impl(normalized.x, normalized.y);
    }

    /**
     *
     * @return the length of the vector.
     */
    @Override
    public double length() {
        return this.vector.getMagnitude();
    }

    /**
     *
     * @param other The other operator.
     * @return the dot product between the two vectors.
     */
    @Override
    public double dot(final Vector2 other) {
        return this.vector.dot(((Vector2Impl) other).getVector());
    }

    /**
     *
     * @return the underlying vector from the library.
     */
    private org.dyn4j.geometry.Vector2 getVector() {
        return this.vector;
    }
}
