package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import org.dyn4j.geometry.Shape;

/**
 * A hitbox interface to manage collisions and resolve them between objects.
 */
public interface Hitbox {
    /**
     * Checks if this hitbox is colliding with another
     * @param other the other hitbox to check.
     * @return true if the hitboxes are colliding.
     */
    boolean collidesWith(final Hitbox other);

    /**
     * Sets where the hitbox is located.
     * @param pos the positions of the hitbox.
     */
    void setPosition(final Pair<Float, Float> pos);

    /**
     *
     * @return the position of the hitbox.
     */
    Pair<Float, Float> getPosition();

    /**
     *
     * @return the shape of the hitbox.
     */
    Shape getShape();
}
