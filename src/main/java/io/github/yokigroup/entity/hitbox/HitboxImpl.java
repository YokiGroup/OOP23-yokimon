package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;

public abstract class HitboxImpl implements Hitbox {
    private Pair<Float, Float> position;

    public HitboxImpl(final Pair<Float, Float> position) {
        this.position = position;
    }

    public HitboxImpl() {
        this(new PairImpl<>(0.0f, 0.0f));
    }

    @Override
    public void setPosition(Pair<Float, Float> pos) {
        this.position = pos;
    }

    @Override
    public Pair<Float, Float> getPosition() {
        return this.position;
    }
}
