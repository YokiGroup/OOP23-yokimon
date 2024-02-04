package io.github.yokigroup.world;

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
     * @return a vector containing the coordinate offset to push an entity in bounds.
     */
    Vector2<float> fixBounds(final Entity entity);
}
