package io.github.yokigroup.world.gen;

import io.github.yokigroup.world.tile.Tile;
import javafx.util.Pair;

import java.util.Set;

/**
 * A wrapper for a Wave Function Collapse library, can also be used for an implementation of it.
 */
public interface WFCWrapper {
    /**
     * Initializes the wave function collapse algorithm with the tile shape's rules.
     * @param dimensions The dimensions of the 2d map.
     * @param shapes All the shapes that will be generated.
     */
    void initializeWFC(final Pair<Integer, Integer> dimensions, final Set<TileShape> shapes);

    /**
     * Sets one of the tiles in the map to be a specific one.
     * @param position The position to set static.
     * @param tile The tile that will be placed there.
     */
    void setStaticTile(final Pair<Integer, Integer> position, final Tile tile);

    /**
     * Runs the wave function collapse algorithm, generating a full tile map.
     */
    void runWFC();

    /**
     *
     * @param position The position to get the tile from.
     * @return The tile at that position.
     */
    Tile getTileAt(final Pair<Integer, Integer> position);
}
