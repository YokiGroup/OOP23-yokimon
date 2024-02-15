package io.github.yokigroup.world;

import io.github.yokigroup.file.loader.TileShapeLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.WFCWrapper;
import io.github.yokigroup.world.gen.WFCWrapperImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a builder class to create a GameMap object.
 */
public class GameMapBuilderImpl implements GameMapBuilder {
    private final Map<Pair<Integer, Integer>, Tile> tileMap;
    private Pair<Integer, Integer> playerTileMapPosition;
    private Pair<Integer, Integer> mapDimensions;

    /**
     * Creates a 1x1 map with the player starting at 0, 0.
     */
    public GameMapBuilderImpl() {
        this.tileMap = new HashMap<>();
        this.playerTileMapPosition = new Pair<>(0, 0);
        this.mapDimensions = new Pair<>(1, 1);
    }

    @Override
    public final GameMapBuilder setMapDimensions(final Pair<Integer, Integer> dimensions) {
        this.mapDimensions = dimensions;
        return this;
    }

    @Override
    public final GameMapBuilder setPlayerTileMapPosition(final Pair<Integer, Integer> position) {
        this.playerTileMapPosition = position;
        return this;
    }

    @Override
    public final GameMapBuilder setStaticTileAt(final Pair<Integer, Integer> position, final Tile tile) {
        this.tileMap.put(position, tile);
        return this;
    }

    @Override
    public GameMap build() {
        final TileShapeLoader loader = new TileShapeLoader();
        final WFCWrapper wfc = new WFCWrapperImpl(this.mapDimensions, loader.getAll());
        this.tileMap.forEach((k, v) -> wfc.setStaticTile(k, v.getAdjacencies()));
        wfc.runWFC();
        for (int i = 0; i < mapDimensions.x(); i++) {
            for (int j = 0; j < mapDimensions.y(); j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                if (!tileMap.contains(pos)) {
                    tileMap.put(pos, wfc.getTileAt(pos));
                }
            }
        }
        return new GameMapImpl(mapDimensions, tileMap, playerTileMapPosition);
    }
}
