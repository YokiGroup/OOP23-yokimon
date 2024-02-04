package io.github.yokigroup.world;

import javafx.util.Pair;

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
     * @return a pair containing the coordinate offset to push an entity in bounds.
     */
    Pair<Float, Float> fixBounds(final Entity entity);
}
