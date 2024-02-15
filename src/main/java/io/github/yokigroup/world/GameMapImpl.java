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
    private Pair<Integer, Integer> playerTileMapPosition;

    /**
     * Initializes the game map through the usage of the wave function collapse algorithm.
     * @param mapDimensions The mapDimensions of the map in tiles.
     */
    public GameMapImpl(final Pair<Integer, Integer> mapDimensions, final Map<Pair<Integer, Integer>, Tile> tileMap, final Pair<Integer, Integer> playerTileMapPosition) {
        if (tileMap == null) {
            throw new IllegalArgumentException("The passed TileMap was null");
        }
        this.playerTileMapPosition = playerTileMapPosition;
        this.mapDimensions = mapDimensions;
        this.tileMap = Map.copyOf(tileMap);
    }

    @Override
    public final Tile getTileAt(final Pair<Integer, Integer> position) {
        return this.tileMap.get(position);
    }

    @Override
    public final Pair<Integer, Integer> getPlayerTileMapPosition() {
        return new Pair<>(this.playerTileMapPosition.x(), this.playerTileMapPosition.y());
    }

    @Override
    public final Tile getPlayerTile() {
        return getTileAt(this.playerTileMapPosition);
    }

    @Override
    public final boolean movePlayerTileMapPosition(final Direction direction) {
        for (final Direction dir : Direction.values()) {
            if (getPlayerTile().getAdjacencies().contains(dir)) {
                this.playerTileMapPosition = new Pair<>(
                        this.playerTileMapPosition.x() + dir.getOffset().x(),
                        this.playerTileMapPosition.y() + dir.getOffset().y()
                );
                if (playerTileMapPosition.x() < 0 || playerTileMapPosition.y() < 0
                        || playerTileMapPosition.x() >= mapDimensions.x() || playerTileMapPosition.y() >= mapDimensions.y()) {
                    throw new IllegalStateException("The player went out of bounds ("
                            + playerTileMapPosition.y() + " " + playerTileMapPosition.y() + ").");
                }
                return true;
            }
        }
        return false;
    }
}
