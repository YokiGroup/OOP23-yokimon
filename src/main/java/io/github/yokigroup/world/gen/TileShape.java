package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

/**
 * A collection of tiles of a specific shape.
 */
public interface TileShape {
    /**
     * The possible directions a tile can connect to.
     */
    enum TileDirections {
        /**
         * The tile can connect upwards.
         */
        UP,
        /**
         * The tile can connect downwards.
         */
        DOWN,
        /**
         * The tile can connect left.
         */
        LEFT,
        /**
         * The tile can connect right.
         */
        RIGHT
    }

    /**
     *
     * @return A randomized pool containing all the tiles of this shape.
     */
    WeightedPool<Tile> getTiles();

    /**
     *
     * @return All the possible directions of this shape.
     */
    Set<TileDirections> getPossibleDirections();
}
