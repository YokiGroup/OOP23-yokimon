package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Transform;
import org.dyn4j.world.DetectFilter;
import org.dyn4j.world.World;
import org.dyn4j.world.result.ConvexDetectResult;

import java.util.Iterator;

/**
 * An abstract binding to the Shape class.
 */
public abstract class HitboxImpl implements Hitbox {
    private final Body body;

    /**
     * Creates a hitbox.
     * @param body The body of the hitbox.
     * @param position The position of the hitbox.
     */
    protected HitboxImpl(final Body body, final Vector2 position) {
        this.body = body;
        this.setPosition(position);
    }

    /**
     * Creates hitbox at the origin.
     * @param body The body of the hitbox.
     */
    protected HitboxImpl(final Body body) {
        this(body, new Vector2Impl(0.0d, 0.0d));
    }

    /**
     * Checks the collision between two hitboxes.
     * @param other the other static hitbox to check.
     * @return true if two hitboxes intersect, false otherwise.
     */
    @Override
    public boolean collidesWith(final Hitbox other) {
        final World<Body> world = new World<>();
        world.addBody(other.getBody());
        DetectFilter<Body, BodyFixture> filter = new DetectFilter<>(true, true, null);
        Iterator<ConvexDetectResult<Body, BodyFixture>> results = world.detectIterator(
                this.body.getFixture(0).getShape(),
                this.body.getTransform(),
                filter
        );
        boolean collides = results.hasNext();
        world.removeBody(other.getBody());
        return collides;
    }

    @Override
    public final void setPosition(final Vector2 pos) {
        final Transform transform = this.body.getTransform().copy();
        transform.setTranslation(pos.getX(), pos.getY());
        this.body.setTransform(transform);
    }

    @Override
    public final Vector2 getPosition() {
        return new Vector2Impl(
                this.body.getTransform().getTranslationX(),
                this.body.getTransform().getTranslationY()
        );
    }

    @Override
    public final Body getBody() {
        final Body bodyCopy = new Body();
        this.body.getFixtures()
                .forEach(f -> bodyCopy.addFixture(new BodyFixture(f.getShape())));
        bodyCopy.translate(getPosition().getX(), getPosition().getY());
        return bodyCopy;
    }
}
