package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of the wave function collapse algorithm.
 * Used for a 2D map generation using a set of shapes.
 */
public class WaveFunctionCollapseImpl implements WaveFunctionCollapse {
    private final Map<Pair<Integer, Integer>, WeightedPool<WfcShape>> shapeMap;

    /**
     * Instantiates the wave function collapse algorithm for the shape map generation.
     * @param dimensions The dimensions of the ShapeMap to generate
     * @param shapes The shapes the map can have.
     */
    public WaveFunctionCollapseImpl(final Pair<Integer, Integer> dimensions, final Set<WfcShape> shapes) {
        this.shapeMap = new HashMap<>();
        // Fill up the map with all shapes being any random shape
        for (int i = 0; i < dimensions.getX(); i++) {
            for (int j = 0; j < dimensions.getY(); j++) {
                final WeightedPool<WfcShape> pool = new WeightedPoolImpl<>();
                shapes.forEach(s -> pool.addElement(s, 1.0f));
                this.shapeMap.put(new PairImpl<>(i, j), pool);
            }
        }
    }

    @Override
    public final WfcShape getShapeAt(final Pair<Integer, Integer> position) {
        // If there's more than one possibility per position, the algorithm has not finished generating the map.
        if (shapeMap.get(position).size() > 1) {
            throw new RuntimeException("The WaveFunctionCollapse algorithm has not finished generating the map.");
        }
        return shapeMap.get(position).getRandomizedElement();
    }

    @Override
    public final void setStaticShape(final Pair<Integer, Integer> position, final WfcShape shape) {
        final WeightedPool<WfcShape> pool = new WeightedPoolImpl<>();
        pool.addElement(shape, 1.0f);
        this.shapeMap.put(position, pool);
    }

    @Override
    public void generateShapeMap() {
        // TODO: add wave function collapse algorithm
    }
}
