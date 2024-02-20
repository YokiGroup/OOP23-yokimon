package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.Direction;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of the wave function collapse algorithm.
 * Used for a 2D map generation using a set of shapes.
 */
public class WaveFunctionCollapseImpl implements WaveFunctionCollapse {
    private final int maxDepth;
    private final Pair<Integer, Integer> dimensions;
    private final Map<Pair<Integer, Integer>, WeightedPool<Set<Direction>>> shapeMap;

    /**
     * Instantiates the wave function collapse algorithm for the shape map generation.
     * @param dimensions The dimensions of the ShapeMap to generate
     * @param shapes The shapes the map can have.
     * @throws IllegalArgumentException If the shape is null.
     */
    public WaveFunctionCollapseImpl(final Pair<Integer, Integer> dimensions, final Set<Set<Direction>> shapes) {
        if (shapes == null) {
            throw new IllegalArgumentException("The WaveFunctionCollapse's shape set must not be null.");
        }
        // Arbitrary max depth
        this.maxDepth = shapes.size() / 2;
        this.dimensions = dimensions;
        this.shapeMap = new HashMap<>();
        // Fill up the map with all shapes being any random shape
        final Set<Set<Direction>> copiedShapes = Set.copyOf(shapes);
        this.getAllValidPositions().forEach(p -> {
            final WeightedPool<Set<Direction>> pool = new WeightedPoolImpl<>();
            copiedShapes.forEach(s -> pool.addElement(Set.copyOf(s), 1.0f));
            this.shapeMap.put(p, pool);
        });
        this.setStaticBorders(copiedShapes);
    }

    /**
     *
     * @return A set with all the positions in the shape map.
     */
    private Set<Pair<Integer, Integer>> getAllValidPositions() {
        final Set<Pair<Integer, Integer>> positions = new HashSet<>();
        for (int i = 0; i < dimensions.x(); i++) {
            for (int j = 0; j < dimensions.y(); j++) {
                positions.add(new Pair<>(i, j));
            }
        }
        return positions;
    }

    @Override
    public final Set<Direction> getShapeAt(final Pair<Integer, Integer> position) {
        // If there's more than one possibility per position, the algorithm has not enough tiles to generate the map.
        if (shapeMap.get(position).size() > 1) {
            throw new IllegalStateException("The WaveFunctionCollapse algorithm has not generated the map correctly.");
        }
        return Map.copyOf(shapeMap).get(position).getRandomizedElement();
    }

    @Override
    public final void setStaticShape(final Pair<Integer, Integer> position, final Set<Set<Direction>> shape) {
        if (shape == null || shape.isEmpty()) {
            throw new IllegalArgumentException("The shape of the static tile must not be empty.");
        }
        if (!checkBounds(position)) {
            throw new IllegalArgumentException("The position must be inside the bounds of the map.");
        }
        final WeightedPool<Set<Direction>> pool = new WeightedPoolImpl<>();
        Set.copyOf(shape).forEach(s -> pool.addElement(Set.copyOf(s), 1.0f));
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
        final Pair<Integer, Integer> centerPos = new Pair<>(dimensions.x() / 2, dimensions.y() / 2);
        Set<Pair<Integer, Integer>> unreachablePos = getUnreachablePositions(centerPos);
        while (!unreachablePos.isEmpty()) {
            final Pair<Integer, Integer> closestPos = getClosestPos(centerPos, unreachablePos);
            final Set<Direction> directions = EnumSet.allOf(Direction.class);
            directions.removeAll(getShapeAt(closestPos));
            final WeightedPool<Direction> randomDirs = new WeightedPoolImpl<>();
            directions.forEach(p -> randomDirs.addElement(p, 1.0f));
            getShapeAt(closestPos).add(randomDirs.getRandomizedElement());
            unreachablePos = getUnreachablePositions(centerPos);
        }
    }

    /**
     *
     * @param start The reference position.
     * @param positions The positions to get the closest from.
     * @return The closest position to start from the positions set.
     */
    private Pair<Integer, Integer> getClosestPos(final Pair<Integer, Integer> start, final Set<Pair<Integer, Integer>> positions) {
        if (positions == null || positions.isEmpty()) {
            throw new IllegalArgumentException("The passed position set was empty or null.");
        }
        return positions.stream()
                .map(Vector2Impl::castPair)
                .reduce((a, b) -> Vector2Impl.castPair(start).minus(a).length() > Vector2Impl.castPair(start).minus(b).length() ? b : a)
                .map(a -> new Pair<>((int) a.getX(), (int) a.getY()))
                .get();
    }

    /**
     *
     * @param pos The starting position of the search.
     * @return A set containing the coordinates of unreachable positions on the map.
     */
    private Set<Pair<Integer, Integer>> getUnreachablePositions(final Pair<Integer, Integer> pos) {
        final Set<Pair<Integer, Integer>> visitedPositions = new HashSet<>();
        // Arbitrary position values to flood-fill from
        floodFill(pos, visitedPositions);
        final Set<Pair<Integer, Integer>> notVisitedPositions = getAllValidPositions();
        notVisitedPositions.removeAll(visitedPositions);
        return notVisitedPositions;
    }

    /**
     * Starts a flood-filling algorithm from a given position to get all the adjacent shapes.
     * @param pos The starting position.
     * @param visitedPositions The mutable set of visited positions.
     */
    private void floodFill(final Pair<Integer, Integer> pos, final Set<Pair<Integer, Integer>> visitedPositions) {
        getShapeAt(pos).forEach(d -> {
            final Pair<Integer, Integer> offsetPos = new Pair<>(
                    pos.x() + d.getOffset().x(),
                    pos.y() + d.getOffset().y()
            );
            if (!visitedPositions.contains(offsetPos)) {
                visitedPositions.add(offsetPos);
                floodFill(offsetPos, visitedPositions);
            }
        });
    }

    /**
     *
     * @param dir The direction to get.
     * @param shapes All the shapes to query.
     * @return A new shape set that doesn't have that direction.
     */
    private Set<Set<Direction>> getTilesWithoutDir(final Direction dir, final Set<Set<Direction>> shapes) {
        return shapes.stream()
                .filter(s -> !s.contains(dir))
                .collect(Collectors.toSet());
    }

    /**
     * Sets the borders of the shapeMap to not contain connections.
     * For example a shape on the left border cannot connect to a shape
     * in the "LEFT" direction.
     * @param shapes The shapes that can be assigned.
     */
    private void setStaticBorders(final Set<Set<Direction>> shapes) {
        // Get the shapes that don't have a specific direction
        final Set<Set<Direction>> topShapes = getTilesWithoutDir(Direction.UP, shapes);
        final Set<Set<Direction>> bottomShapes = getTilesWithoutDir(Direction.DOWN, shapes);
        final Set<Set<Direction>> leftShapes = getTilesWithoutDir(Direction.LEFT, shapes);
        final Set<Set<Direction>> rightShapes = getTilesWithoutDir(Direction.RIGHT, shapes);
        // Get the shapes that can go in the corner (without 2 directions)
        final Set<Set<Direction>> topLeftShapes = new HashSet<>(leftShapes);
        topLeftShapes.retainAll(topShapes);
        final Set<Set<Direction>> topRightShapes = new HashSet<>(rightShapes);
        topRightShapes.retainAll(topShapes);
        final Set<Set<Direction>> bottomLeftShapes = new HashSet<>(leftShapes);
        bottomLeftShapes.retainAll(bottomShapes);
        final Set<Set<Direction>> bottomRightShapes = new HashSet<>(rightShapes);
        bottomRightShapes.retainAll(bottomShapes);
        // Get the corners of the map
        final Pair<Integer, Integer> topLeft = new Pair<>(0, 0);
        final Pair<Integer, Integer> topRight = new Pair<>(this.dimensions.x() - 1, 0);
        final Pair<Integer, Integer> bottomLeft = new Pair<>(0, this.dimensions.y() - 1);
        final Pair<Integer, Integer> bottomRight = new Pair<>(this.dimensions.x() - 1, this.dimensions.y() - 1);
        // Set the horizontal (top and bottom) walls
        for (int i = 1; i < this.dimensions.x() - 1; i++) {
            final Pair<Integer, Integer> posTop = new Pair<>(i, 0);
            final Pair<Integer, Integer> posBottom = new Pair<>(i, this.dimensions.y() - 1);
            this.setStaticShape(posTop, topShapes);
            this.setStaticShape(posBottom, bottomShapes);
        }
        // Set the vertical (left and right) walls
        for (int j = 1; j < this.dimensions.y() - 1; j++) {
            final Pair<Integer, Integer> posLeft = new Pair<>(0, j);
            final Pair<Integer, Integer> posRight = new Pair<>(this.dimensions.x() - 1, j);
            this.setStaticShape(posLeft, leftShapes);
            this.setStaticShape(posRight, rightShapes);
        }
        // Set the corners
        this.setStaticShape(topLeft, topLeftShapes);
        this.setStaticShape(topRight, topRightShapes);
        this.setStaticShape(bottomLeft, bottomLeftShapes);
        this.setStaticShape(bottomRight, bottomRightShapes);
    }

    /**
     * Updates all the adjacent WfcShapes to the central position.
     * @param depth The depth of the update search.
     * @param centerPosition The central position of the update.
     */
    private void updateAdjacentShapes(final int depth, final Pair<Integer, Integer> centerPosition) {
        // Get its coherence table
        final Set<Direction> coherenceShape = getCoherentDirections(this.shapeMap.get(centerPosition).getEntries());
        // If there's no coherence it means there can no longer be updates in this path.
        if (coherenceShape.isEmpty()) {
            return;
        }
        // Get any shape from the center
        final Set<Direction> centerShape = this.shapeMap.get(centerPosition).getRandomizedElement();
        // Check all directions
        for (final Direction dir : coherenceShape) {
            // Get the coordinate offset for that direction
            final Pair<Integer, Integer> offsetPos = new Pair<>(
                    centerPosition.x() + dir.getOffset().x(),
                    centerPosition.y() + dir.getOffset().y()
            );
            // Check if the position is in bounds, and if the tile has not already been collapsed
            if (checkBounds(offsetPos) && !hasBeenCollapsed(offsetPos)) {
                // And get the connection for that direction
                final Direction dirConnection = dir.getComplementary();
                // Get the pool from the tile to update
                final WeightedPool<Set<Direction>> pool = this.shapeMap.get(offsetPos);
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
    private Set<Direction> getCoherentDirections(final Set<Set<Direction>> shapes) {
        // Get the common shapes through a stream
        final Set<Direction> commonShapes = shapes.stream()
                .reduce((a, b) -> {
                    final Set<Direction> common = new HashSet<>(a);
                    common.retainAll(b);
                    return common;
                }).orElse(Collections.emptySet());
        // Get all the common non-present directions by removing them from the set containing all the direction
        final Set<Direction> uncommonShapes = EnumSet.allOf(Direction.class);
        shapes.forEach(uncommonShapes::removeAll);
        // Merge the two sets together and return them
        final Set<Direction> coherentShapes = new HashSet<>(commonShapes);
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
