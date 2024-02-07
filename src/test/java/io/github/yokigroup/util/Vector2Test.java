package io.github.yokigroup.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {

    @Test
    void getters() {
        final Vector2 vec1 = new Vector2Impl();
        assertEquals(0.0d, vec1.getX());
        assertEquals(0.0d, vec1.getY());
        final Vector2 vec2 = new Vector2Impl(3.0d, 2.3d);
        assertEquals(3.0d, vec2.getX());
        assertEquals(2.3d, vec2.getY());
    }

    @Test
    void operations() {
        final Vector2 vec1 = new Vector2Impl(6.0d, 8.0d);
        final Vector2 vec2 = new Vector2Impl(3.0d, 4.0d);
        final Vector2 sum = vec1.plus(vec2);
        assertEquals(9.0d, sum.getX());
        assertEquals(12.0d, sum.getY());
        final Vector2 sub = vec1.minus(vec2);
        assertEquals(3.0d, sub.getX());
        assertEquals(4.0d, sub.getY());
        final Vector2 multi = vec1.times(vec2);
        assertEquals(18.0d, multi.getX());
        assertEquals(32.0d, multi.getY());
        final Vector2 div = vec1.divide(vec2);
        assertEquals(2.0d, div.getX());
        assertEquals(2.0d, div.getY());
        final Vector2 scaled1 = vec1.scale(3.0d);
        final Vector2 scaled2 = vec2.scale(0.5d);
        assertEquals(18.0d, scaled1.getX());
        assertEquals(24.0d, scaled1.getY());
        assertEquals(1.5d, scaled2.getX());
        assertEquals(2.0d, scaled2.getY());
        // Checking to not have edited the initial vectors
        assertEquals(6.0d, vec1.getX());
        assertEquals(8.0d, vec1.getY());
        assertEquals(3.0d, vec2.getX());
        assertEquals(4.0d, vec2.getY());
    }

    @Test
    void normalize() {
        final Vector2 vec1 = new Vector2Impl(0.0d, 3.0d);
        final Vector2 vec2 = new Vector2Impl(3.0d, 0.0d);
        final Vector2 vec3 = new Vector2Impl(10.4d, 6.0d);
        final Vector2 vec1Normalized = vec1.normalize();
        final Vector2 vec2Normalized = vec2.normalize();
        final Vector2 vec3Normalized = vec3.normalize();
        assertEquals(0.0d, vec1Normalized.getX());
        assertEquals(1.0d, vec1Normalized.getY());
        assertEquals(1.0d, vec2Normalized.getX());
        assertEquals(0.0d, vec2Normalized.getY());
        assertEquals(0.8661855860486004d, vec3Normalized.getX());
        assertEquals(0.4997224534895771d, vec3Normalized.getY());
        // Checking to not have edited the initial vectors
        assertEquals(0.0d, vec1.getX());
        assertEquals(3.0d, vec1.getY());
        assertEquals(3.0d, vec2.getX());
        assertEquals(0.0d, vec2.getY());
        assertEquals(10.4d, vec3.getX());
        assertEquals(6.0d, vec3.getY());
    }

    @Test
    void length() {
        final Vector2 vec1 = new Vector2Impl(0.0d, 3.0d);
        final Vector2 vec2 = new Vector2Impl(3.0d, 0.0d);
        final Vector2 vec3 = new Vector2Impl(10.4d, 6.0d);
        assertEquals(3.0d, vec1.length());
        assertEquals(3.0d, vec2.length());
        assertEquals(12.006664815842909d, vec3.length());
        // Checking to not have edited the initial vectors
        assertEquals(0.0d, vec1.getX());
        assertEquals(3.0d, vec1.getY());
        assertEquals(3.0d, vec2.getX());
        assertEquals(0.0d, vec2.getY());
        assertEquals(10.4d, vec3.getX());
        assertEquals(6.0d, vec3.getY());
    }

    @Test
    void dot() {
        final Vector2 vec1 = new Vector2Impl(0.0d, 3.0d);
        final Vector2 vec2 = new Vector2Impl(3.0d, 0.0d);
        final Vector2 vec3 = new Vector2Impl(10.0d, 6.0d);
        assertEquals(0.0d, vec1.dot(vec2));
        assertEquals(0.0d, vec2.dot(vec1));
        assertEquals(30.0d, vec2.dot(vec3));
        assertEquals(30.0d, vec3.dot(vec2));
        assertEquals(18.0d, vec1.dot(vec3));
        assertEquals(18.0d, vec3.dot(vec1));
        // Checking to not have edited the initial vectors
        assertEquals(0.0d, vec1.getX());
        assertEquals(3.0d, vec1.getY());
        assertEquals(3.0d, vec2.getX());
        assertEquals(0.0d, vec2.getY());
        assertEquals(10.0d, vec3.getX());
        assertEquals(6.0d, vec3.getY());
    }
}
