package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.WFCWrapper;
import io.github.yokigroup.world.gen.WFCWrapperImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The map of the game.
 * Contains tiles, their entities and the player world position.
 * Allows the player to switch screens (tiles) by walking around.
 */
public class GameMapImpl implements GameMap {
    private final WFCWrapper wfc;
    private final Map<Pair<Integer, Integer>, Tile> tileMap;
    private Pair<Integer, Integer> worldPlayerPosition;

    /**
     * Initializes the game map through the usage of the wave function collapse algorithm.
     * @param dimensions The dimensions of the map in tiles.
     */
    public GameMapImpl(final Pair<Integer, Integer> dimensions) {
        // TODO: add proper wfc initialization
        this.wfc = new WFCWrapperImpl(dimensions, Collections.emptySet());
        this.tileMap = new HashMap<>();
        // TODO: initialize the map through the WFC algorithm
        this.worldPlayerPosition = new Pair<>(dimensions.x() / 2, dimensions.y() / 2);
    }

    @Override
    public final Tile getTileAt(final Pair<Integer, Integer> position) {
        return this.tileMap.get(position);
    }

    @Override
    public final Pair<Integer, Integer> getPlayerWorldPosition() {
        return new Pair<>(this.worldPlayerPosition.x(), this.worldPlayerPosition.y());
    }

    @Override
    public final Tile getPlayerTile() {
        return getTileAt(this.worldPlayerPosition);
    }
}
