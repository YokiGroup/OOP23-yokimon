package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.PairImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HitboxTest {

    @Test
    void collidesWithCircles() {
        final Hitbox circleHtibox1 = new CircularHitbox(new PairImpl<>(1.2f, 3.2f), 4.0f);
        final Hitbox circleHtibox2 = new CircularHitbox(new PairImpl<>(7.2f, -1.15f), 6.0f);
        final Hitbox circleHtibox3 = new CircularHitbox(new PairImpl<>(12.7f, 4.0f), 2.0f);
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
        final Hitbox rectangleHtibox1 = new RectangularHitbox(new PairImpl<>(4.2f, 5.7f), new PairImpl<>(4.7f, 4.2f));
        final Hitbox rectangleHtibox2 = new RectangularHitbox(new PairImpl<>(9.0f, 0.0f), new PairImpl<>(2.4f, 5.0f));
        final Hitbox rectangleHtibox3 = new RectangularHitbox(new PairImpl<>(5.0f, 2.0f), new PairImpl<>(10.0f, 4.0f));
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
        final Hitbox circleHtibox1 = new CircularHitbox(new PairImpl<>(1.2f, 3.2f), 4.0f);
        final Hitbox circleHtibox2 = new CircularHitbox(new PairImpl<>(7.2f, -1.15f), 6.0f);
        final Hitbox circleHtibox3 = new CircularHitbox(new PairImpl<>(12.7f, 4.0f), 2.0f);
        final Hitbox rectangleHtibox1 = new RectangularHitbox(new PairImpl<>(4.2f, 5.7f), new PairImpl<>(4.7f, 4.2f));
        final Hitbox rectangleHtibox2 = new RectangularHitbox(new PairImpl<>(9.0f, 0.0f), new PairImpl<>(2.4f, 5.0f));
        final Hitbox rectangleHtibox3 = new RectangularHitbox(new PairImpl<>(5.0f, 2.0f), new PairImpl<>(10.0f, 4.0f));
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
