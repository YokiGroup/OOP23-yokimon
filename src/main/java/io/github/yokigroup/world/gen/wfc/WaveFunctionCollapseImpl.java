package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Optional;


/**
 * An implementation of the wave function collapse algorithm.
 * Used for a 2D map generation using a set of shapes.
 */
public class WaveFunctionCollapseImpl implements WaveFunctionCollapse {
    private final int maxDepth;
    private final Pair<Integer, Integer> dimensions;
    private final Map<Pair<Integer, Integer>, WeightedPool<Set<WfcShapeDirection>>> shapeMap;

    /**
     * Instantiates the wave function collapse algorithm for the shape map generation.
     * @param dimensions The dimensions of the ShapeMap to generate
     * @param shapes The shapes the map can have.
     */
    public WaveFunctionCollapseImpl(final Pair<Integer, Integer> dimensions, final Set<Set<WfcShapeDirection>> shapes) {
        // Arbitrary max depth
        this.maxDepth = shapes.size();
        this.dimensions = dimensions;
        this.shapeMap = new HashMap<>();
        // Fill up the map with all shapes being any random shape
        for (int i = 0; i < dimensions.x(); i++) {
            for (int j = 0; j < dimensions.y(); j++) {
                final WeightedPool<Set<WfcShapeDirection>> pool = new WeightedPoolImpl<>();
                shapes.forEach(s -> pool.addElement(s, 1.0f));
                this.shapeMap.put(new Pair<>(i, j), pool);
            }
        }
    }

    @Override
    public final Set<WfcShapeDirection> getShapeAt(final Pair<Integer, Integer> position) {
        // If there's more than one possibility per position, the algorithm has not finished generating the map.
        if (shapeMap.get(position).size() > 1) {
            throw new IllegalStateException("The WaveFunctionCollapse algorithm has not yet finished generating the map.");
        }
        return shapeMap.get(position).getRandomizedElement();
    }

    @Override
    public final void setStaticShape(final Pair<Integer, Integer> position, final Set<Set<WfcShapeDirection>> shape) {
        if (shape == null || shape.isEmpty()) {
            throw new IllegalArgumentException("The shape of the static tile must not be empty.");
        }
        if (!checkBounds(position)) {
            throw new IllegalArgumentException("The position must be inside the bounds of the map.");
        }
        final WeightedPool<Set<WfcShapeDirection>> pool = new WeightedPoolImpl<>();
        shape.forEach(s -> pool.addElement(s, 1.0f));
        this.shapeMap.put(position, pool);
        updateAdjacentShapes(maxDepth, position);
    }

    /**
     *
     * @param pos The position to check.
     * @return True if the position is a position inside the bounds of the map.
     */
    private boolean checkBounds(final Pair<Integer, Integer> pos) {
        return pos.x() >= 0 && pos.y() >= 0 && pos.x() < this.dimensions.x() && pos.y() < this.dimensions.y();
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
     * @param depth The depth of the update search.
     * @param centerPosition The central position of the update.
     */
    private void updateAdjacentShapes(final int depth, final Pair<Integer, Integer> centerPosition) {
        // Get any shape from the center
        final Set<WfcShapeDirection> centerShape = this.shapeMap.get(centerPosition).getRandomizedElement();
        // Get its coherence table
        final Set<WfcShapeDirection> coherenceShape = getCoherentDirections(this.shapeMap.get(centerPosition).getEntries());
        // Check all directions
        for (final WfcShapeDirection dir : coherenceShape) {
            // Get the coordinate offset for that direction
            final Pair<Integer, Integer> offsetPos = new Pair<>(
                    centerPosition.x() + dir.getOffset().x(),
                    centerPosition.y() + dir.getOffset().y()
            );
            // Check if the position is in bounds, and if the tile has not already been collapsed
            if (checkBounds(offsetPos) && !hasBeenCollapsed(offsetPos)) {
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
                if (depth > 1) {
                    updateAdjacentShapes(depth - 1, offsetPos);
                }
            }
        }
    }

    /**
     * Checks if a direction has been set on a shape, for example if a shape has the options: (UP, LEFT) and (UP, RIGHT)
     * it will return a new shape containing the values: (UP, DOWN).
     * @param shapes The shapes to check.
     * @return A set of enums containing where the random shape has always the same direction.
     */
    public Set<WfcShapeDirection> getCoherentDirections(final Set<Set<WfcShapeDirection>> shapes) {
        // Get the common shapes through a stream
        final Set<WfcShapeDirection> commonShapes = shapes.stream()
                .reduce((a, b) -> {
                    final Set<WfcShapeDirection> common = new HashSet<>(a);
                    common.retainAll(b);
                    return common;
                }).orElse(EnumSet.noneOf(WfcShapeDirection.class));
        // Get all the common non-present directions by removing them from the set containing all the direction
        final Set<WfcShapeDirection> uncommonShapes = EnumSet.allOf(WfcShapeDirection.class);
        shapes.forEach(uncommonShapes::removeAll);
        // Merge the two sets together and return them
        final Set<WfcShapeDirection> coherentShapes = new HashSet<>(commonShapes);
        coherentShapes.addAll(uncommonShapes);
        return coherentShapes;
    }

    /**
     *
     * @param pos The position to check.
     * @return True if the shapeMap at that position has been collapsed.
     */
    private boolean hasBeenCollapsed(final Pair<Integer, Integer> pos) {
        return this.shapeMap.get(pos).size() == 1;
    }

    /**
     * It chooses a random WfcShape of the pool to set that position to.
     * @param position The position of the shapeMap to collapse.
     */
    private void collapseShape(final Pair<Integer, Integer> position) {
        setStaticShape(position, Set.of(this.shapeMap.get(position).getRandomizedElement()));
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
