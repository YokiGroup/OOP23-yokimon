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
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    /**
     *
     * @return A randomized pool containing all the tiles of this shape.
     */
    WeightedPool<Tile> getTiles();

    /**
     *
     * @return All the directions of this shape.
     */
    Set<TileDirections> getPossibleDirections();
}
