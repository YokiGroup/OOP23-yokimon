package io.github.yokigroup.util;

/**
 * A numeric vector2.
 * @param <T> The type of the vector.
 */
public interface Vector2<T extends Number> {

    /**
     *
     * @return the first component of the vector
     */
    T getX();

    /**
     *
     * @return the second component of the vector.
     */
    T getY();

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the addition between this and another vector (component per component).
     */
    Vector2<T> add(Vector2<T> other);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the subtraction between this and another vector (component per component).
     */
    Vector2<T> sub(Vector2<T> other);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the multiplication between this and another vector (component per component).
     */
    Vector2<T> mult(Vector2<T> other);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the division between this and another vector (component per component).
     */
    Vector2<T> div(Vector2<T> other);


    /**
     *
     * @param value The value to scale the vector by.
     * @return a new vector scaled by that value.
     */
    Vector2<T> scale(T value);

    /**
     *
     * @return a new normalized vector (with length 1).
     */
    Vector2<T> normalize();

    /**
     *
     * @return the length of the vector.
     */
    T length();

    /**
     *
     * @param other The other operator.
     * @return the dot product between the two vectors.
     */
    T dot(Vector2<T> other);
}
