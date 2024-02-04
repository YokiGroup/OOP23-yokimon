package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.world.Hitbox;
import javafx.util.Pair;

/**
 * A circular hitbox type.
 */
public class CircleHitbox implements Hitbox  {
    private Pair<Float, Float> centerPosition;
    private float radius;

    public CircleHitbox(final Pair<Float, Float> position, final float radius) {
        this.centerPosition = position;
        this.radius = radius;
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
