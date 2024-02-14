package io.github.yokigroup.util;

/**
 * A Vector2 binding to a library.
 */
public interface Vector2 {

    /**
     *
     * @return the first component of the vector
     */
    double getX();

    /**
     *
     * @return the second component of the vector.
     */
    double getY();

    /**
     * Sums two vectors together.
     * @param other The other opertator.
     * @return a new vector containing the addition between this and another vector (component per component).
     */
    Vector2 plus(Vector2 other);

    /**
     * Subtracts two vectors together.
     * @param other The other opertator.
     * @return a new vector containing the subtraction between this and another vector (component per component).
     */
    Vector2 minus(Vector2 other);

    /**
     * Multiplies two vectors together.
     * @param other The other opertator.
     * @return a new vector containing the multiplication between this and another vector (component per component).
     */
    Vector2 times(Vector2 other);

    /**
     * Divides two vectors together.
     * @param other The other opertator.
     * @return a new vector containing the division between this and another vector (component per component).
     */
    Vector2 divide(Vector2 other);


    /**
     * Multiplies each component of the vector by value.
     * @param value The value to scale the vector by.
     * @return a new vector scaled by that value.
     */
    Vector2 scale(double value);

    /**
     * Normalizes the vector.
     * @return a new normalized vector (with length 1).
     */
    Vector2 normalize();

    /**
     *
     * @return the length of the vector.
     */
    double length();

    /**
     * Calculates the dot product between two vectors.
     * @param other The other operator.
     * @return the dot product between the two vectors.
     */
    double dot(Vector2 other);

    /**
     *
     * @param other The other vector to check.
     * @return True if the two vectors are the same, false otherwise.
     */
    boolean equals(Vector2 other);
}
