package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.tile.TileBuilder;

import java.util.Set;

/**
 * A wrapper for a Wave Function Collapse library, can also be used for an implementation of it.
 */
public interface WFCWrapper {

    /**
     * Sets one of the tiles in the map to be a specific one.
     * @param position The position to set static.
     * @param tile The tile that will be placed there.
     */
    void setStaticTile(Pair<Integer, Integer> position, Set<Direction> tile);

    /**
     * Runs the wave function collapse algorithm, generating a full tile map.
     */
    void runWFC();

    /**
     *
     * @param position The position to get the tile from.
     * @return The tile at that position.
     */
    TileBuilder getTileAt(Pair<Integer, Integer> position);
}
