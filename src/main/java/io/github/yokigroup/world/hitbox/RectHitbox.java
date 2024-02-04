package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.world.Hitbox;
import javafx.util.Pair;

/**
 * A rectangular hitbox type.
 */
public class RectHitbox implements Hitbox {
    private Pair<Float, Float> centerPosition;
    private Pair<Float, Float> dimensions;

    public RectHitbox(final Pair<Float, Float> position, final Pair<Float, Float> dimensions) {
        this.centerPosition = position;
        this.dimensions = dimensions;
    }

    @Override
    public boolean collidesWith(final Hitbox hitbox) {
        return false;
    }

    @Override
    public Pair<Float, Float> fixBounds(final Entity entity) {
        return null;
    }
}
