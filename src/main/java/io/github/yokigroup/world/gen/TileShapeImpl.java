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
        if (shape == null) {
            throw new IllegalArgumentException("The 'shape' argument is null.");
        } else if (tiles == null) {
            throw new IllegalArgumentException("The 'tiles' argument is null");
        }
        this.shape = Set.copyOf(shape);
        this.tiles = new WeightedPoolImpl<>();
        for (final Tile t : Set.copyOf(tiles)) {
            this.tiles.addElement(t, 1.0f);
        }
    }

    @Override
    public final WeightedPool<Tile> getTiles() {
        return new WeightedPoolImpl<>(this.tiles);
    }

    @Override
    public final Set<TileDirections> getPossibleDirections() {
        return Set.copyOf(this.shape);
    }
}
