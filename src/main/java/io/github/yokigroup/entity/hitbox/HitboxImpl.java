package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import org.dyn4j.geometry.Shape;

public abstract class HitboxImpl implements Hitbox {
    private final Shape shape;
    private Pair<Float, Float> position;

    public HitboxImpl(final Shape shape, final Pair<Float, Float> position) {
        this.position = position;
        this.shape = shape;
    }

    public HitboxImpl(final Shape shape) {
        this(shape, new PairImpl<>(0.0f, 0.0f));
    }

    @Override
    public void setPosition(final Pair<Float, Float> pos) {
        this.position = pos;
    }

    @Override
    public Pair<Float, Float> getPosition() {
        return this.position;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }
}
