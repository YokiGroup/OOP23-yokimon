package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.util.Vector2;

/**
 * A collision box that allows for easier collision detection and manipulation
 */
public interface Hitbox {
    /**
     *
     * @param hitbox The other hitbox to compare.
     * @return true if the two hitboxes are intersecting.
     */
    boolean collidesWith(final Hitbox hitbox);

    /**
     *
     * @param entity The entity to move back in bounds.
     * @return a pair containing the coordinate offset to push an entity back in bounds.
     */
    Vector2<Float> fixBounds(final Entity entity);
}
