package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

/**
 * A TileShape contains a set of all the tiles of a specific shape.
 */
public class TileShapeImpl implements TileShape {
    private final Set<TileDirections> shape;
    private final WeightedPool<Tile> tiles;

    /**
     * Creates a TileShape set, containing all the tiles of a specific shape.
     * @param tiles The tiles of this shape.
     * @param shape The shape of the tile.
     */
    public TileShapeImpl(final Set<Tile> tiles, final Set<TileDirections> shape) {
        this.shape = shape;
        this.tiles = new WeightedPoolImpl<>();
        for (final Tile t : tiles) {
            this.tiles.addElement(t, 1.0f);
        }
    }

    /**
     *
     * @return A randomized pool containing all the tiles of this shape.
     */
    @Override
    public WeightedPool<Tile> getTiles() {
        return tiles.clone();
    }

    /**
     *
     * @return All the possible directions of this shape.
     */
    @Override
    public Set<TileDirections> getPossibleDirections() {
        return Set.copyOf(this.shape);
    }
}
