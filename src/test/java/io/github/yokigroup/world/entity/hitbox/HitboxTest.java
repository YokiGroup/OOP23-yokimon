package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HitboxTest {
    private static Hitbox circleHtibox1;
    private static Hitbox circleHtibox2;
    private static Hitbox circleHtibox3;
    private static Hitbox rectangleHtibox1;
    private static Hitbox rectangleHtibox2;
    private static Hitbox rectangleHtibox3;

    @BeforeEach
    public void init() {
        final double x1 = 1.2d;
        final double y1 = 3.2d;
        final double x2 = 7.2d;
        final double y2 = -1.15d;
        final double x3 = 12.7d;
        final double y3 = 4.0d;
        final double r1 = 4.0d;
        final double r2 = 6.0d;
        final double r3 = 2.0d;
        final double x4 = 4.2d;
        final double y4 = 5.7d;
        final double x5 = 9.0d;
        final double y5 = 0.0d;
        final double x6 = 5.0d;
        final double y6 = 2.0d;
        final double w1 = 4.7d;
        final double h1 = 4.2d;
        final double w2 = 2.4d;
        final double h2 = 5.0d;
        final double w3 = 10.0d;
        final double h3 = 4.0d;
        circleHtibox1 = new CircularHitbox(new Vector2Impl(x1, y1), r1);
        circleHtibox2 = new CircularHitbox(new Vector2Impl(x2, y2), r2);
        circleHtibox3 = new CircularHitbox(new Vector2Impl(x3, y3), r3);
        rectangleHtibox1 = new RectangularHitbox(new Vector2Impl(x4, y4), new Vector2Impl(w1, h1));
        rectangleHtibox2 = new RectangularHitbox(new Vector2Impl(x5, y5), new Vector2Impl(w2, h2));
        rectangleHtibox3 = new RectangularHitbox(new Vector2Impl(x6, y6), new Vector2Impl(w3, h3));
    }

    @Test
    void collidesWithCircles() {
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

    @Test
    void changePosition() {
        final Vector2 newPos = new Vector2Impl(circleHtibox2.getPosition().getX() + 3.0d, circleHtibox2.getPosition().getY());
        circleHtibox2.setPosition(newPos);
        assertTrue(circleHtibox1.collidesWith(circleHtibox2));
        assertTrue(circleHtibox2.collidesWith(circleHtibox1));
        final Vector2 newPos2 = new Vector2Impl(circleHtibox2.getPosition().getX() + 3.0d, circleHtibox2.getPosition().getY());
        circleHtibox2.setPosition(newPos2);
        assertFalse(circleHtibox1.collidesWith(circleHtibox2));
        assertFalse(circleHtibox2.collidesWith(circleHtibox1));
    }
}
