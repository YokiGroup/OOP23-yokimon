package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.people.Enemy;
import io.github.yokigroup.world.tile.Tile;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * The map of the game.
 * Contains tiles, their entities and the player world position.
 * Allows the player to switch screens (tiles) by walking around.
 */
class GameMapImpl implements GameMap {
    private final Map<Pair<Integer, Integer>, Tile> tileMap;
    private final Pair<Integer, Integer> mapDimensions;
    private Pair<Integer, Integer> playerTileMapPosition;

    /**
     * Initializes the game map through the usage of the wave function collapse algorithm.
     * @param mapDimensions The mapDimensions of the map in tiles.
     * @param tileMap The map of the tiles.
     * @param playerTileMapPosition The player position on the tileMap.
     * @throws IllegalArgumentException if the TileMap is null.
     */
    GameMapImpl(final Pair<Integer, Integer> mapDimensions, final Map<Pair<Integer, Integer>, Tile> tileMap,
                final Pair<Integer, Integer> playerTileMapPosition) {
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
    public boolean areAllEnemiesSlain() {
        final Optional<Entity> aliveEntities = this.tileMap.values().stream()
                .map(Tile::getEntities)
                .flatMap(Collection::stream)
                .filter(e -> e instanceof Enemy)
                .filter(e -> ((Enemy) e).isActive())
                .findAny();
        return aliveEntities.isEmpty();
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
        if (getPlayerTile().getAdjacencies().contains(direction)) {
            final Pair<Integer, Integer> tentativePlayerMapPos = new Pair<>(
                    this.playerTileMapPosition.x() + direction.getOffset().x(),
                    this.playerTileMapPosition.y() + direction.getOffset().y()
            );
            if (tentativePlayerMapPos.x() < 0 || tentativePlayerMapPos.y() < 0
                    || tentativePlayerMapPos.x() >= mapDimensions.x() || tentativePlayerMapPos.y() >= mapDimensions.y()) {
                return false;
            }
            this.playerTileMapPosition = tentativePlayerMapPos;
            return true;
        }
        return false;
    }
}
