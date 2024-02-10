package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

public class WFCWrapperImpl implements WFCWrapper {
    private final WaveFunctionCollapse wfc = new WaveFunctionCollapseImpl();

    /**
     * Initializes the wave function collapse algorithm with the tile shape's rules.
     * @param dimensions The dimensions of the 2d map.
     * @param shapes All the shapes that will be generated.
     */
    public WFCWrapperImpl(final Pair<Integer, Integer> dimensions, final Set<TileShape> shapes) {

    }

    @Override
    public void setStaticTile(final Pair<Integer, Integer> position, final Tile tile) {

    }

    @Override
    public void runWFC() {

    }

    @Override
    public Tile getTileAt(final Pair<Integer, Integer> position) {
        return null;
    }
}
