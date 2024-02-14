package io.github.yokigroup.world;

import io.github.yokigroup.file.loader.TileShapeLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.TileShape;
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
    private final TileShapeLoader tileShapeLoader;
    private final Pair<Integer, Integer> tileDimensions;
    private final Map<Pair<Integer, Integer>, Tile> tileMap;
    private Pair<Integer, Integer> worldPlayerPosition;

    /**
     * Initializes the game map through the usage of the wave function collapse algorithm.
     * @param mapDimensions The mapDimensions of the map in tiles.
     */
    public GameMapImpl(final Pair<Integer, Integer> tileDimensions, final Pair<Integer, Integer> mapDimensions) {
        this.tileDimensions = tileDimensions;
        this.tileShapeLoader = new TileShapeLoader();
        final WFCWrapper wfc = new WFCWrapperImpl(mapDimensions, tileShapeLoader.getAll());
        this.tileMap = new HashMap<>();
        for (int i = 0; i < mapDimensions.x(); i++) {
            for (int j = 0; j < mapDimensions.y(); j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                final Tile mapTile = wfc.getTileAt(pos);
                // TODO: maybe spawn entities here.
                this.tileMap.put(pos, mapTile);
            }
        }
        this.worldPlayerPosition = new Pair<>(mapDimensions.x() / 2, mapDimensions.y() / 2);
    }

    @Override
    public final Tile getTileAt(final Pair<Integer, Integer> position) {
        return this.tileMap.get(position);
    }

    @Override
    public Pair<Integer, Integer> getTileDimensions() {
        return this.tileDimensions;
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
