package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HitboxTest {
    private static Hitbox circleHitbox1;
    private static Hitbox circleHitbox2;
    private static Hitbox circleHitbox3;
    private static Hitbox rectangleHitbox1;
    private static Hitbox rectangleHitbox2;
    private static Hitbox rectangleHitbox3;

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
        circleHitbox1 = new CircularHitbox(new Vector2Impl(x1, y1), r1);
        circleHitbox2 = new CircularHitbox(new Vector2Impl(x2, y2), r2);
        circleHitbox3 = new CircularHitbox(new Vector2Impl(x3, y3), r3);
        rectangleHitbox1 = new RectangularHitbox(new Vector2Impl(x4, y4), new Vector2Impl(w1, h1));
        rectangleHitbox2 = new RectangularHitbox(new Vector2Impl(x5, y5), new Vector2Impl(w2, h2));
        rectangleHitbox3 = new RectangularHitbox(new Vector2Impl(x6, y6), new Vector2Impl(w3, h3));
    }

    @Test
    void collidesWithCircles() {
        // Collisions between circles only
        assertTrue(circleHitbox1.collidesWith(circleHitbox2).isPresent());
        assertTrue(circleHitbox2.collidesWith(circleHitbox1).isPresent());
        assertFalse(circleHitbox1.collidesWith(circleHitbox3).isPresent());
        assertFalse(circleHitbox3.collidesWith(circleHitbox1).isPresent());
        assertTrue(circleHitbox2.collidesWith(circleHitbox3).isPresent());
        assertTrue(circleHitbox3.collidesWith(circleHitbox2).isPresent());
    }

    @Test
    void collidesWithRectangles() {
        // Collisions between rectangles only
        assertFalse(rectangleHitbox1.collidesWith(rectangleHitbox2).isPresent());
        assertFalse(rectangleHitbox2.collidesWith(rectangleHitbox1).isPresent());
        assertTrue(rectangleHitbox1.collidesWith(rectangleHitbox3).isPresent());
        assertTrue(rectangleHitbox3.collidesWith(rectangleHitbox1).isPresent());
        assertTrue(rectangleHitbox2.collidesWith(rectangleHitbox3).isPresent());
        assertTrue(rectangleHitbox3.collidesWith(rectangleHitbox2).isPresent());
    }

    @Test
    void collidesWithBoth() {
        // Collisions of boxes with circle1
        assertTrue(circleHitbox1.collidesWith(rectangleHitbox1).isPresent());
        assertTrue(rectangleHitbox1.collidesWith(circleHitbox1).isPresent());
        assertFalse(circleHitbox1.collidesWith(rectangleHitbox2).isPresent());
        assertFalse(rectangleHitbox2.collidesWith(circleHitbox1).isPresent());
        assertTrue(circleHitbox1.collidesWith(rectangleHitbox3).isPresent());
        assertTrue(rectangleHitbox3.collidesWith(circleHitbox1).isPresent());
        // Collisions of boxes with circle2
        assertTrue(circleHitbox2.collidesWith(rectangleHitbox1).isPresent());
        assertTrue(rectangleHitbox1.collidesWith(circleHitbox2).isPresent());
        assertTrue(circleHitbox2.collidesWith(rectangleHitbox2).isPresent());
        assertTrue(rectangleHitbox2.collidesWith(circleHitbox2).isPresent());
        assertTrue(circleHitbox2.collidesWith(rectangleHitbox3).isPresent());
        assertTrue(rectangleHitbox3.collidesWith(circleHitbox2).isPresent());
        // Collisions of boxes with circle3
        assertFalse(circleHitbox3.collidesWith(rectangleHitbox1).isPresent());
        assertFalse(rectangleHitbox1.collidesWith(circleHitbox3).isPresent());
        assertFalse(circleHitbox3.collidesWith(rectangleHitbox2).isPresent());
        assertFalse(rectangleHitbox2.collidesWith(circleHitbox3).isPresent());
        assertFalse(circleHitbox3.collidesWith(rectangleHitbox3).isPresent());
        assertFalse(rectangleHitbox3.collidesWith(circleHitbox3).isPresent());
    }

    @Test
    void resolveCollisions() {
        assertTrue(circleHitbox1.collidesWith(circleHitbox2).isPresent());
        assertTrue(circleHitbox2.collidesWith(circleHitbox1).isPresent());
        final Vector2 resolvedPos = circleHitbox1.collidesWith(circleHitbox2).get();
        circleHitbox1.setPosition(circleHitbox1.getPosition().plus(resolvedPos));
        assertFalse(circleHitbox1.collidesWith(circleHitbox2).isPresent());
        assertFalse(circleHitbox2.collidesWith(circleHitbox1).isPresent());
        assertTrue(rectangleHitbox1.collidesWith(rectangleHitbox3).isPresent());
        assertTrue(rectangleHitbox3.collidesWith(rectangleHitbox1).isPresent());
        final Vector2 resolvedPos2 = rectangleHitbox1.collidesWith(rectangleHitbox3).get();
        rectangleHitbox1.setPosition(rectangleHitbox1.getPosition().plus(resolvedPos2));
        assertFalse(rectangleHitbox1.collidesWith(rectangleHitbox3).isPresent());
        assertFalse(rectangleHitbox3.collidesWith(rectangleHitbox1).isPresent());
        assertTrue(circleHitbox2.collidesWith(rectangleHitbox3).isPresent());
        assertTrue(rectangleHitbox3.collidesWith(circleHitbox2).isPresent());
        final Vector2 resolvedPos3 = circleHitbox2.collidesWith(rectangleHitbox3).get();
        circleHitbox2.setPosition(circleHitbox2.getPosition().plus(resolvedPos3));
        assertFalse(circleHitbox2.collidesWith(rectangleHitbox3).isPresent());
        assertFalse(rectangleHitbox3.collidesWith(circleHitbox2).isPresent());
    }

    @Test
    void changePosition() {
        final Vector2 newPos = new Vector2Impl(circleHitbox2.getPosition().getX() + 3.0d, circleHitbox2.getPosition().getY());
        circleHitbox2.setPosition(newPos);
        assertTrue(circleHitbox1.collidesWith(circleHitbox2).isPresent());
        assertTrue(circleHitbox2.collidesWith(circleHitbox1).isPresent());
        final Vector2 newPos2 = new Vector2Impl(circleHitbox2.getPosition().getX() + 3.0d, circleHitbox2.getPosition().getY());
        circleHitbox2.setPosition(newPos2);
        assertFalse(circleHitbox1.collidesWith(circleHitbox2).isPresent());
        assertFalse(circleHitbox2.collidesWith(circleHitbox1).isPresent());
    }
}
