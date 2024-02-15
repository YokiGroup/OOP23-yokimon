package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;

import java.util.Map;

/**
 * The map of the game.
 * Contains tiles, their entities and the player world position.
 * Allows the player to switch screens (tiles) by walking around.
 */
public class GameMapImpl implements GameMap {
    private final Map<Pair<Integer, Integer>, Tile> tileMap;
    private final Pair<Integer, Integer> mapDimensions;
    private Pair<Integer, Integer> worldPlayerPosition;

    /**
     * Initializes the game map through the usage of the wave function collapse algorithm.
     * @param mapDimensions The mapDimensions of the map in tiles.
     */
    public GameMapImpl(final Pair<Integer, Integer> mapDimensions, final Map<Pair<Integer, Integer>, Tile> tileMap, final Pair<Integer, Integer> worldPlayerPosition) {
        this.tileMap = tileMap;
        this.worldPlayerPosition = worldPlayerPosition;
        this.mapDimensions = mapDimensions;
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

    @Override
    public final boolean movePlayerWorldPosition(final Direction direction) {
        // TODO: to implement
        return true;
    }
}
