package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

public interface TileShape {
    enum Direction {
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
    Set<Direction> getPossibleDirections();
}
