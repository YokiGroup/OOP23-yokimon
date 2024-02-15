package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;

/**
 * Interface of an Object which represent a Position, used Entity.
 */
public interface Position {

    /**
     * Return the position.
     * @return Vector
     */
    Vector2 getPosition();

    /**
     * Set a new position, if it's not valid... .
     * @param pos new position
     */
    void setPosition(Vector2 pos);

    /**
     * add a vector to the position.
     * @param vector vector to add
     */
    void movePosition(Vector2 vector);

    /**
     * add a vector to the position and return the new value of pos without changing it.
     * @param vector vector to add
     * @return Position new
     */
    Position testMovePosition(Vector2 vector);
    /**
     * Return true if the position is valid in the map.
     * False if is not.
     * @return boolean
     */
    boolean isValid();

    /**
     * Return true if the distance from the pair passed is less then Radius
     * Return false if radius is greater.
     * @param otherPos the other pair to check
     * @param radius min distance
     * @return boolean
     */
    boolean inRadius(Position otherPos, double radius);


}
