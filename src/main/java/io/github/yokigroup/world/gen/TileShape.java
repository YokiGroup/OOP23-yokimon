package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.tile.TileBuilder;

import java.util.Set;

/**
 * A collection of tiles of a specific shape.
 */
public interface TileShape {
    /**
     *
     * @return A randomized pool containing all the tiles of this shape.
     */
    WeightedPool<TileBuilder> getTiles();

    /**
     *
     * @return All the possible directions of this shape.
     */
    Set<Direction> getPossibleDirections();
}
