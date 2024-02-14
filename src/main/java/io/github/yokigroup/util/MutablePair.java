package io.github.yokigroup.util;

/**
 * A binding to a data structure containing two elements, which can eventually change.
 */
public interface MutablePair{
    /**
     *
     * @return the first component of the pair
     */
    double getX();

    /**
     *
     * @return the second component of the pair.
     */
    double getY();

    /**
     *
     * @return the pair object
     */
    MutablePair getPair();
    /**
     * Set the x with another value
     * @param x T
     */
    void setX(double x);

    /**
     * Set the y with another value
     * @param y K
     */
    void setY(double y);

    /**
     * Set x and y with a new value
     * @param newPair new values of the pair
     */
    void setPair(MutablePair newPair);

    /**
     * Add a vector to the current pair
     * @param vector vector
     */
    void addVector(Vector2 vector);


    /**
     *
     * @return return a copy of this pair
     */
    public MutablePair copyOf();
}
