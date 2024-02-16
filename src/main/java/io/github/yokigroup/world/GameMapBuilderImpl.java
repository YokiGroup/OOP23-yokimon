package io.github.yokigroup.world;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.TileLoader;
import io.github.yokigroup.file.loader.TileShapeLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.WFCWrapper;
import io.github.yokigroup.world.gen.WFCWrapperImpl;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of a builder class to create a GameMap object.
 */
public class GameMapBuilderImpl implements GameMapBuilder {
    private final Map<Pair<Integer, Integer>, TileBuilder> tileMap;
    private Pair<Integer, Integer> playerTileMapPosition;
    private Pair<Integer, Integer> mapDimensions;
    private TileLoader tileLoader = new TileLoader();

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

    /*
    @Override
    public final GameMapBuilder setStaticTileAt(final Pair<Integer, Integer> position, final Tile tile) {
        this.tileMap.put(position, tile);
        return this;
    }
    */

    @Override
    public GameMapBuilder setHomeTileAt(Pair<Integer, Integer> position) {
        this.tileMap.put(position, tileLoader.getHomeTile());
        return this;
    }

    @Override
    public GameMap build(MessageHandler handler) {
        final TileShapeLoader loader = new TileShapeLoader(tileLoader);
        final WFCWrapper wfc = new WFCWrapperImpl(this.mapDimensions, loader.getAll());
        final Map<Pair<Integer, Integer>, Tile> builtTiles = tileMap.entrySet().stream()
                .map(a -> new Pair<>(a.getKey(), a.getValue().build(handler)))
                .collect(Collectors.toMap(Pair::x, Pair::y));
        builtTiles.forEach((k, v) -> wfc.setStaticTile(k, v.getAdjacencies()));
        wfc.runWFC();
        for (int i = 0; i < mapDimensions.x(); i++) {
            for (int j = 0; j < mapDimensions.y(); j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                if (!builtTiles.containsKey(pos)) {
                    builtTiles.put(pos, wfc.getTileAt(pos).build(handler));
                }
            }
        }
        return new GameMapImpl(mapDimensions, builtTiles, playerTileMapPosition);
    }
}
