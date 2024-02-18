package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Transform;
import org.dyn4j.world.DetectFilter;
import org.dyn4j.world.World;
import org.dyn4j.world.result.ConvexDetectResult;

import java.util.Iterator;
import java.util.Optional;

/**
 * An abstract binding to the Shape class.
 */
public abstract class HitboxImpl implements Hitbox {
    private static final double LAMBDA = 1E-10d;
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

    @Override
    public final Optional<Vector2> collidesWith(final Hitbox other) {
        if (other == null) {
            return Optional.empty();
        }
        // Add the body to the world to check for collisions
        final World<Body> world = new World<>();
        world.addBody(((HitboxImpl) other).getBody());
        // Check for bounding box collisions
        final DetectFilter<Body, BodyFixture> filter = new DetectFilter<>(true, true, null);
        final Iterator<ConvexDetectResult<Body, BodyFixture>> results = world.detectIterator(
                // If you see any Fixture cast it's to suppress one of SpotBugs warnings, it's a library issue.
                ((Fixture) this.body.getFixture(0)).getShape(),
                this.body.getTransform(),
                filter
        );
        // Calculate the minimum translation vector to correct the collision (if there is one)
        Optional<Vector2> mtv = Optional.empty();
        if (results.hasNext()) {
            final ConvexDetectResult<Body, BodyFixture> result = results.next();
            // Get the penetration amount of the collision
            final Vector2 offset = getIntersectionVector(result);
            // Shapes still have the bounding boxes colliding, so we check if the offset
            // is long enough to be considered a collision (sometimes it can return 0.0d, 0.0d as values, or
            // some very small offset values like 1E-20)
            if (offset.length() > LAMBDA) {
                mtv = Optional.of(offset);
            }
        }
        world.removeAllBodies();
        return mtv;
    }

    /**
     *
     * @param detectResult The convexDetection result to get the vector of.
     * @return The offset to resolve the Hitbox collision from the detection.
     */
    private static Vector2 getIntersectionVector(final ConvexDetectResult<Body, BodyFixture> detectResult) {
        final double penetration = detectResult.getPenetration().getDepth();
        // Get the normal vector of the collision (the direction we should move the body by to fix the collision
        final Vector2 normal = new Vector2Impl(
                detectResult.getPenetration().getNormal().x,
                detectResult.getPenetration().getNormal().y
        );
        // Scale the normal by the penetration amount to resolve the collision
        return normal.scale(penetration);
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
    public final boolean equals(final Object o) {
        if (!(o instanceof HitboxImpl)) {
            return false;
        } else if (
                // If you see any Fixture cast it's to suppress one of SpotBugs warnings, it's a library issue.
                ((Fixture) this.body.getFixture(0)).getShape().getClass()
                != ((Fixture) ((HitboxImpl) o).getBody().getFixture(0)).getShape().getClass()
        ) {
            return false;
        }
        final AABB aabb1 = this.body.createAABB();
        final AABB aabb2 = ((HitboxImpl) o).getBody().createAABB();
        return this.body.getTransform().getTranslationX() == ((HitboxImpl) o).getBody().getTransform().getTranslationX()
                && aabb1.getHeight() == aabb2.getHeight()
                && aabb1.getWidth() == aabb2.getWidth();
    }

    @Override
    public final int hashCode() {
        final int prime1 = 79;
        final int prime2 = 39;
        // If you see any Fixture cast it's to suppress one of SpotBugs warnings, it's a library issue.
        return prime1 * this.getPosition().hashCode() + prime2
                * (int) ((Fixture) this.body.getFixture(0)).getShape().getRadius();
    }

    /**
     *
     * @return The underlying body of the Hitbox.
     */
    protected final Body getBody() {
        final Body bodyCopy = new Body();
        this.body.getFixtures()
                .forEach(f -> bodyCopy.addFixture(new BodyFixture(f.getShape())));
        bodyCopy.translate(getPosition().getX(), getPosition().getY());
        return bodyCopy;
    }
}
