package io.github.yokigroup.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Vector2Test {

    @Test
    void getters() {
        final double coord1X = 0.0d;
        final double coord1Y = 0.0d;
        final double coord2X = 3.0d;
        final double coord2Y = 2.3d;
        final Vector2 vec1 = new Vector2Impl();
        assertEquals(coord1X, vec1.getX());
        assertEquals(coord1Y, vec1.getY());
        final Vector2 vec2 = new Vector2Impl(coord2X, coord2Y);
        assertEquals(coord2X, vec2.getX());
        assertEquals(coord2Y, vec2.getY());
    }

    @Test
    void operations() {
        final double coord1X = 6.0d;
        final double coord1Y = 8.0d;
        final double coord2X = 3.0d;
        final double coord2Y = 4.0d;
        final double sumX = 9.0d;
        final double sumY = 12.0d;
        final double subX = 3.0d;
        final double subY = 4.0d;
        final double multiX = 18.0d;
        final double multiY = 32.0d;
        final double divX = 2.0d;
        final double divY = 2.0d;
        final double scaleAmount1 = 3.0d;
        final double scaleAmount2 = 0.5d;
        final double scaled1X = 18.0d;
        final double scaled1Y = 24.0d;
        final double scaled2X = 1.5d;
        final double scaled2Y = 2.0d;
        final Vector2 vec1 = new Vector2Impl(coord1X, coord1Y);
        final Vector2 vec2 = new Vector2Impl(coord2X, coord2Y);
        final Vector2 sum = vec1.plus(vec2);
        assertEquals(sumX, sum.getX());
        assertEquals(sumY, sum.getY());
        final Vector2 sub = vec1.minus(vec2);
        assertEquals(subX, sub.getX());
        assertEquals(subY, sub.getY());
        final Vector2 multi = vec1.times(vec2);
        assertEquals(multiX, multi.getX());
        assertEquals(multiY, multi.getY());
        final Vector2 div = vec1.divide(vec2);
        assertEquals(divX, div.getX());
        assertEquals(divY, div.getY());
        final Vector2 scaled1 = vec1.scale(scaleAmount1);
        final Vector2 scaled2 = vec2.scale(scaleAmount2);
        assertEquals(scaled1X, scaled1.getX());
        assertEquals(scaled1Y, scaled1.getY());
        assertEquals(scaled2X, scaled2.getX());
        assertEquals(scaled2Y, scaled2.getY());
        // Checking to not have edited the initial vectors
        assertEquals(coord1X, vec1.getX());
        assertEquals(coord1Y, vec1.getY());
        assertEquals(coord2X, vec2.getX());
        assertEquals(coord2Y, vec2.getY());
    }

    @Test
    void normalize() {
        final double coord1X = 0.0d;
        final double coord1Y = 3.0d;
        final double coord2X = 3.0d;
        final double coord2Y = 0.0d;
        final double coord3X = 10.4d;
        final double coord3Y = 6.0d;
        final double normCoord1X = 0.0d;
        final double normCoord1Y = 1.0d;
        final double normCoord2X = 1.0d;
        final double normCoord2Y = 0.0d;
        final double normCoord3X = 0.866_185_586_048_600_400d;
        final double normCoord3Y = 0.499_722_453_489_577_100d;
        final Vector2 vec1 = new Vector2Impl(coord1X, coord1Y);
        final Vector2 vec2 = new Vector2Impl(coord2X, coord2Y);
        final Vector2 vec3 = new Vector2Impl(coord3X, coord3Y);
        final Vector2 vec1Normalized = vec1.normalize();
        final Vector2 vec2Normalized = vec2.normalize();
        final Vector2 vec3Normalized = vec3.normalize();
        assertEquals(normCoord1X, vec1Normalized.getX());
        assertEquals(normCoord1Y, vec1Normalized.getY());
        assertEquals(normCoord2X, vec2Normalized.getX());
        assertEquals(normCoord2Y, vec2Normalized.getY());
        assertEquals(normCoord3X, vec3Normalized.getX());
        assertEquals(normCoord3Y, vec3Normalized.getY());
        // Checking to not have edited the initial vectors
        assertEquals(coord1X, vec1.getX());
        assertEquals(coord1Y, vec1.getY());
        assertEquals(coord2X, vec2.getX());
        assertEquals(coord2Y, vec2.getY());
        assertEquals(coord3X, vec3.getX());
        assertEquals(coord3Y, vec3.getY());
    }

    @Test
    void length() {
        final double coord1X = 0.0d;
        final double coord1Y = 3.0d;
        final double coord2X = 3.0d;
        final double coord2Y = 0.0d;
        final double coord3X = 10.4d;
        final double coord3Y = 6.0d;
        final double length1 = 3.0d;
        final double length2 = 3.0d;
        final double length3 = 12.006_664_815_842_909d;
        final Vector2 vec1 = new Vector2Impl(coord1X, coord1Y);
        final Vector2 vec2 = new Vector2Impl(coord2X, coord2Y);
        final Vector2 vec3 = new Vector2Impl(coord3X, coord3Y);
        assertEquals(length1, vec1.length());
        assertEquals(length2, vec2.length());
        assertEquals(length3, vec3.length());
        // Checking to not have edited the initial vectors
        assertEquals(coord1X, vec1.getX());
        assertEquals(coord1Y, vec1.getY());
        assertEquals(coord2X, vec2.getX());
        assertEquals(coord2Y, vec2.getY());
        assertEquals(coord3X, vec3.getX());
        assertEquals(coord3Y, vec3.getY());
    }

    @Test
    void dot() {
        final double coord1X = 0.0d;
        final double coord1Y = 3.0d;
        final double coord2X = 3.0d;
        final double coord2Y = 0.0d;
        final double coord3X = 10.0d;
        final double coord3Y = 6.0d;
        final double dot12 = 0.0d;
        final double dot23 = 30.0d;
        final double dot31 = 18.0d;
        final Vector2 vec1 = new Vector2Impl(coord1X, coord1Y);
        final Vector2 vec2 = new Vector2Impl(coord2X, coord2Y);
        final Vector2 vec3 = new Vector2Impl(coord3X, coord3Y);
        assertEquals(dot12, vec1.dot(vec2));
        assertEquals(dot12, vec2.dot(vec1));
        assertEquals(dot23, vec2.dot(vec3));
        assertEquals(dot23, vec3.dot(vec2));
        assertEquals(dot31, vec1.dot(vec3));
        assertEquals(dot31, vec3.dot(vec1));
        // Checking to not have edited the initial vectors
        assertEquals(coord1X, vec1.getX());
        assertEquals(coord1Y, vec1.getY());
        assertEquals(coord2X, vec2.getX());
        assertEquals(coord2Y, vec2.getY());
        assertEquals(coord3X, vec3.getX());
        assertEquals(coord3Y, vec3.getY());
    }
}
