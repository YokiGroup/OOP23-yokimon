package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;

import java.util.Optional;

/**
 * A hitbox interface to manage collisions and resolve them between objects.
 */
public interface Hitbox {
    /**
     * Checks if this hitbox is colliding with another and returns the Vector2 offset
     * to move this hitbox out of collision.
     * @param other The other static hitbox to check.
     * @return A Vector2 offset to move this hitbox by, it's empty if there is no collision.
     */
    Optional<Vector2> collidesWith(Hitbox other);

    /**
     * Changes the position of the hitbox and the shape itself.
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
     * @return A copy of this hitbox.
     */
    Hitbox copyOf();


    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
