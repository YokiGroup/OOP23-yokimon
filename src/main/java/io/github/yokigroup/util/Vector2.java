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
     * Sets the first component of the vector.
     * @param x The value to set it to.
     */
    void setX(double x);

    /**
     * Sets the second component of the vector.
     * @param y The value to set it to.
     */
    void setY(double y);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the addition between this and another vector (component per component).
     */
    Vector2 add(Vector2 other);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the subtraction between this and another vector (component per component).
     */
    Vector2 sub(Vector2 other);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the multiplication between this and another vector (component per component).
     */
    Vector2 mult(Vector2 other);

    /**
     *
     * @param other The other opertator.
     * @return a new vector containing the division between this and another vector (component per component).
     */
    Vector2 div(Vector2 other);


    /**
     *
     * @param value The value to scale the vector by.
     * @return a new vector scaled by that value.
     */
    Vector2 scale(double value);

    /**
     *
     * @return a new normalized vector (with length 1).
     */
    Vector2 normalize();

    /**
     *
     * @return the length of the vector.
     */
    double length();

    /**
     *
     * @param other The other operator.
     * @return the dot product between the two vectors.
     */
    double dot(Vector2 other);
}
