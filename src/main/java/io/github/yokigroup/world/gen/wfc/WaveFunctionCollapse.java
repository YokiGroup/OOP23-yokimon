package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;

import java.util.Set;

/**
 * The interface for the Wave Function Collapse algorithm.
 * It takes in a Set of shapes and the dimensions of the map, it
 * generates a Map of shapes for our map generation.
 */
public interface WaveFunctionCollapse {
    /**
     *
     * @param position The position of the shape to get.
     * @return The shape of the section at that position.
     */
    Set<WfcShapeDirection> getShapeAt(Pair<Integer, Integer> position);

    /**
     * Sets one of the positions on the map to only accept a specific shape.
     * @param position The position of the shape to set.
     * @param shape The shape to set the map to.
     */
    void setStaticShape(Pair<Integer, Integer> position, Set<WfcShapeDirection> shape);

    /**
     * Runs the waveFunctionCollapse algorithm to generate a full shape map of the
     * possible added shapes.
     */
    void generateShapeMap();
}
