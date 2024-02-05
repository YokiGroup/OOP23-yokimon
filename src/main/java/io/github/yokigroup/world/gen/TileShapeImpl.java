package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

public class TileShapeImpl implements TileShape {
    private final Set<TileDirections> shape;
    private final WeightedPool<Tile> tiles;

    public TileShapeImpl(final Set<Tile> tiles, final Set<TileDirections> shape) {
        this.shape = shape;
        this.tiles = new WeightedPoolImpl<>();
        for (final Tile t : tiles) {
            this.tiles.addElement(t, 1.0f);
        }
    }

    @Override
    public WeightedPool<Tile> getTiles() {
        return tiles.clone();
    }

    @Override
    public Set<TileDirections> getPossibleDirections() {
        return Set.copyOf(this.shape);
    }
}
