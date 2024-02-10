package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * An implementation of the wave function collapse algorithm.
 * Used for a 2D map generation using a set of shapes.
 */
public class WaveFunctionCollapseImpl implements WaveFunctionCollapse {
    private final Pair<Integer, Integer> dimensions;
    private final Map<Pair<Integer, Integer>, WeightedPool<Set<WfcShapeDirection>>> shapeMap;

    /**
     * Instantiates the wave function collapse algorithm for the shape map generation.
     * @param dimensions The dimensions of the ShapeMap to generate
     * @param shapes The shapes the map can have.
     */
    public WaveFunctionCollapseImpl(final Pair<Integer, Integer> dimensions, final Set<Set<WfcShapeDirection>> shapes) {
        this.dimensions = dimensions;
        this.shapeMap = new HashMap<>();
        // Fill up the map with all shapes being any random shape
        for (int i = 0; i < dimensions.getX(); i++) {
            for (int j = 0; j < dimensions.getY(); j++) {
                final WeightedPool<Set<WfcShapeDirection>> pool = new WeightedPoolImpl<>();
                shapes.forEach(s -> pool.addElement(s, 1.0f));
                this.shapeMap.put(new PairImpl<>(i, j), pool);
            }
        }
    }

    @Override
    public final Set<WfcShapeDirection> getShapeAt(final Pair<Integer, Integer> position) {
        // If there's more than one possibility per position, the algorithm has not finished generating the map.
        if (shapeMap.get(position).size() > 1) {
            throw new RuntimeException("The WaveFunctionCollapse algorithm has not yet finished generating the map.");
        }
        return shapeMap.get(position).getRandomizedElement();
    }

    @Override
    public final void setStaticShape(final Pair<Integer, Integer> position, final Set<WfcShapeDirection> shape) {
        final WeightedPool<Set<WfcShapeDirection>> pool = new WeightedPoolImpl<>();
        pool.addElement(shape, 1.0f);
        this.shapeMap.put(position, pool);
        updateAdjacentShapes(position);
    }

    /**
     * Generates the full shape map using the wave function collapse algorithm.
     */
    @Override
    public final void generateShapeMap() {
        // Get the lowest entropy in the map
        Optional<Integer> lowestEntropy = getMinimumEntropy();
        while (lowestEntropy.isPresent()) {
            // Get a random position from the shapeMap with the lowest entropy
            final Pair<Integer, Integer> lowestEntropyPosition = getRandomLowestEntropy(lowestEntropy.get());
            // Collapse that position with a random shape and update its neighbors to reflect that
            collapseShape(lowestEntropyPosition);
            // Get the lowest entropy in the map again
            lowestEntropy = getMinimumEntropy();
        }
    }

    /**
     * Updates all the adjacent WfcShapes to the central position.
     * @param centerPosition The central position of the update.
     */
    private void updateAdjacentShapes(final Pair<Integer, Integer> centerPosition) {
        // Get the center shape of the update
        final Set<WfcShapeDirection> centerShape = this.shapeMap.get(centerPosition).getEntries().iterator().next();
        // Check all directions
        for (final WfcShapeDirection dir : WfcShapeDirection.values()) {
            // Get the coordinate offset for that direction
            final Pair<Integer, Integer> offsetPos = new PairImpl<>(
                    centerPosition.getX() + dir.getOffset().getX(),
                    centerPosition.getY() + dir.getOffset().getY()
            );
            if (offsetPos.getX() >= 0 && offsetPos.getY() >= 0 && offsetPos.getX() < dimensions.getX() && offsetPos.getY() < dimensions.getY()) {
                // And get the connection for that direction
                final WfcShapeDirection dirConnection = dir.getConnection();
                // Get the pool from the tile to update
                final WeightedPool<Set<WfcShapeDirection>> pool = this.shapeMap.get(offsetPos);
                // For all the shapes it has
                pool.getEntries()
                        .stream()
                        .filter(s -> centerShape.contains(dir) != s.contains(dirConnection)) // Check if it cannot connect
                        .forEach(pool::removeElement); // And remove it
                // Set that new pool to the ShapeMap
                this.shapeMap.put(offsetPos, pool);
            }
        }
    }

    /**
     * It chooses a random WfcShape of the pool to set that position to.
     * @param position The position of the shapeMap to collapse.
     */
    private void collapseShape(final Pair<Integer, Integer> position) {
        setStaticShape(position, this.shapeMap.get(position).getRandomizedElement());
    }

    /**
     *
     * @return The lowest entropy of the map.
     */
    private Optional<Integer> getMinimumEntropy() {
        // Get the lowest shape count there is
        return this.shapeMap
                .values()
                .stream()
                .map(WeightedPool::size)
                .filter(s -> s > 1) // If the size is 1, then it has already been collapsed
                .reduce(Math::min);
    }

    /**
     *
     * @param minEntropy The minimum entropy of the ShapeMap.
     * @return A random tile of the lowest entropy in the map.
     */
    private Pair<Integer, Integer> getRandomLowestEntropy(final int minEntropy) {
        // Make a randomized pool of all the elements with the lowest entropy
        final WeightedPool<Pair<Integer, Integer>> randomizedPossibilities = new WeightedPoolImpl<>();
        this.shapeMap.entrySet()
                .stream()
                .filter(p -> p.getValue().size() == minEntropy)
                .forEach(p -> randomizedPossibilities.addElement(p.getKey(), 1.0f));
        return randomizedPossibilities.getRandomizedElement();
    }
}
