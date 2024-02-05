package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.util.Pair;

/**
 * An abstract implementation of the common elements of the Hitbox interface.
 */
public abstract class HitboxImpl implements Hitbox {
    protected Pair<Float, Float> centerPos;

    @Override
    public Pair<Float, Float> getPosition() {
        return this.centerPos;
    }

    @Override
    public void setPosition(final Pair<Float, Float> pos) {
        this.centerPos = pos;
    }
}
