package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.util.Pair;

/**
 * A circular hitbox defined by its center and radius.
 */
public class CircleHitbox extends HitboxImpl {

    @Override
    public boolean collidesWith(Hitbox hitbox) {
        return false;
    }

    @Override
    public Pair<Float, Float> fixBounds(Hitbox hitbox) {
        return null;
    }
}
