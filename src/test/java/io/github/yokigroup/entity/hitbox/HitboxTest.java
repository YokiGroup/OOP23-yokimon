package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HitboxTest {

    @Test
    void collidesWithCircles() {
        final Hitbox circleHtibox1 = new CircularHitbox(new Vector2Impl(1.2d, 3.2d), 4.0d);
        final Hitbox circleHtibox2 = new CircularHitbox(new Vector2Impl(7.2d, -1.15d), 6.0d);
        final Hitbox circleHtibox3 = new CircularHitbox(new Vector2Impl(12.7d, 4.0d), 2.0d);
        // Collisions between circles only
        assertTrue(circleHtibox1.collidesWith(circleHtibox2));
        assertTrue(circleHtibox2.collidesWith(circleHtibox1));
        assertFalse(circleHtibox1.collidesWith(circleHtibox3));
        assertFalse(circleHtibox3.collidesWith(circleHtibox1));
        assertTrue(circleHtibox2.collidesWith(circleHtibox3));
        assertTrue(circleHtibox3.collidesWith(circleHtibox2));
    }

    @Test
    void collidesWithRectangles() {
        final Hitbox rectangleHtibox1 = new RectangularHitbox(new Vector2Impl(4.2d, 5.7d), new Vector2Impl(4.7d, 4.2d));
        final Hitbox rectangleHtibox2 = new RectangularHitbox(new Vector2Impl(9.0d, 0.0d), new Vector2Impl(2.4d, 5.0d));
        final Hitbox rectangleHtibox3 = new RectangularHitbox(new Vector2Impl(5.0d, 2.0d), new Vector2Impl(10.0d, 4.0d));
        // Collisions between rectangles only
        assertFalse(rectangleHtibox1.collidesWith(rectangleHtibox2));
        assertFalse(rectangleHtibox2.collidesWith(rectangleHtibox1));
        assertTrue(rectangleHtibox1.collidesWith(rectangleHtibox3));
        assertTrue(rectangleHtibox3.collidesWith(rectangleHtibox1));
        assertTrue(rectangleHtibox2.collidesWith(rectangleHtibox3));
        assertTrue(rectangleHtibox3.collidesWith(rectangleHtibox2));
    }

    @Test
    void collidesWithBoth() {
        final Hitbox circleHtibox1 = new CircularHitbox(new Vector2Impl(1.2d, 3.2d), 4.0d);
        final Hitbox circleHtibox2 = new CircularHitbox(new Vector2Impl(7.2d, -1.15d), 6.0d);
        final Hitbox circleHtibox3 = new CircularHitbox(new Vector2Impl(12.7d, 4.0d), 2.0d);
        final Hitbox rectangleHtibox1 = new RectangularHitbox(new Vector2Impl(4.2d, 5.7d), new Vector2Impl(4.7d, 4.2d));
        final Hitbox rectangleHtibox2 = new RectangularHitbox(new Vector2Impl(9.0d, 0.0d), new Vector2Impl(2.4d, 5.0d));
        final Hitbox rectangleHtibox3 = new RectangularHitbox(new Vector2Impl(5.0d, 2.0d), new Vector2Impl(10.0d, 4.0d));
        // Collisions of boxes with circle1
        assertTrue(circleHtibox1.collidesWith(rectangleHtibox1));
        assertTrue(rectangleHtibox1.collidesWith(circleHtibox1));
        assertFalse(circleHtibox1.collidesWith(rectangleHtibox2));
        assertFalse(rectangleHtibox2.collidesWith(circleHtibox1));
        assertTrue(circleHtibox1.collidesWith(rectangleHtibox3));
        assertTrue(rectangleHtibox3.collidesWith(circleHtibox1));
        // Collisions of boxes with circle2
        assertTrue(circleHtibox2.collidesWith(rectangleHtibox1));
        assertTrue(rectangleHtibox1.collidesWith(circleHtibox2));
        assertTrue(circleHtibox2.collidesWith(rectangleHtibox2));
        assertTrue(rectangleHtibox2.collidesWith(circleHtibox2));
        assertTrue(circleHtibox2.collidesWith(rectangleHtibox3));
        assertTrue(rectangleHtibox3.collidesWith(circleHtibox2));
        // Collisions of boxes with circle3
        assertFalse(circleHtibox3.collidesWith(rectangleHtibox1));
        assertFalse(rectangleHtibox1.collidesWith(circleHtibox3));
        assertFalse(circleHtibox3.collidesWith(rectangleHtibox2));
        assertFalse(rectangleHtibox2.collidesWith(circleHtibox3));
        assertFalse(circleHtibox3.collidesWith(rectangleHtibox3));
        assertFalse(rectangleHtibox3.collidesWith(circleHtibox3));
    }
}
