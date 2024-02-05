package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.util.Pair;

/**
 * A rectangular hitbox defined by its center and dimensions.
 */
public class RectHitbox extends HitboxImpl {
    private final Pair<Float, Float> dimensions;

    public RectHitbox(final Pair<Float, Float> centerPosition, final Pair<Float, Float> dimensions) {
        super(centerPosition);
        this.dimensions = dimensions;
    }

    @Override
    public boolean collidesWith(Hitbox hitbox) {
        return false;
    }

    @Override
    public Pair<Float, Float> fixBounds(Hitbox hitbox) {
        return null;
    }
}
