package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.util.Pair;

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
     * @param hitbox The hitbox to get offset from.
     * @return a vector2 containing the coordinate offset to push an entity back in bounds.
     */
    Pair<Float, Float> fixBounds(final Hitbox hitbox);
}
