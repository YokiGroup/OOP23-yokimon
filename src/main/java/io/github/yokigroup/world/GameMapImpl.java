package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import io.github.yokigroup.world.gen.WFCWrapper;
import io.github.yokigroup.world.gen.WFCWrapperImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameMapImpl implements GameMap {
    private final WFCWrapper wfc;
    private final Map<Pair<Integer, Integer>, Tile> tileMap;
    private Pair<Integer, Integer> worldPlayerPosition;

    public GameMapImpl(final Pair<Integer, Integer> dimensions) {
        // FIXME: add proper wfc initialization
        this.wfc = new WFCWrapperImpl(dimensions, Collections.emptySet());
        this.tileMap = new HashMap<>();
        // TODO: initialize the map through the WFC algorithm
        this.worldPlayerPosition = new PairImpl<>(dimensions.getX() / 2, dimensions.getY() / 2);
    }

    @Override
    public final Tile getTileAt(final Pair<Integer, Integer> position) {
        return this.tileMap.get(position);
    }

    @Override
    public final Pair<Integer, Integer> getPlayerWorldPosition() {
        return this.worldPlayerPosition;
    }

    @Override
    public final Tile getPlayerTile() {
        return getTileAt(this.worldPlayerPosition);
    }
}
