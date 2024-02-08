package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import org.dyn4j.geometry.Shape;

/**
 * A hitbox interface to manage collisions and resolve them between objects.
 */
public interface Hitbox {
    /**
     * Checks if this hitbox is colliding with another.
     * @param other the other static hitbox to check.
     * @return true if the hitboxes are colliding.
     */
    boolean collidesWith(Hitbox other);

    /**
     * Sets where the hitbox is located.
     * @param pos the positions of the hitbox.
     */
    void setPosition(Vector2 pos);

    /**
     *
     * @return the position of the hitbox.
     */
    Vector2 getPosition();

    /**
     *
     * @return the shape of the hitbox.
     */
    Shape getShape();
}
