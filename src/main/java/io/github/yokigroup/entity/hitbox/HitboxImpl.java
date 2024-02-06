package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import org.dyn4j.geometry.Shape;

/**
 * An abstract binding to the Shape class
 */
public abstract class HitboxImpl implements Hitbox {
    private Pair<Float, Float> position;
    protected Shape shape;

    public HitboxImpl(final Pair<Float, Float> position) {
        this.position = position;
    }

    public HitboxImpl() {
        this(new PairImpl<>(0.0f, 0.0f));
    }

    @Override
    public void setPosition(final Pair<Float, Float> pos) {
        this.position = pos;
        this.shape.translate(0, 0);
        this.shape.translate(pos.getX(), pos.getY());
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
