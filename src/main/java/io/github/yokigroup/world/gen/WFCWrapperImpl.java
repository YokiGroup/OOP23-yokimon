package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.wfc.WaveFunctionCollapse;
import io.github.yokigroup.world.gen.wfc.WaveFunctionCollapseImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

/**
 * Implementation of the wave function collapse algorithm wrapper.
 */
public class WFCWrapperImpl implements WFCWrapper {
    private final WaveFunctionCollapse wfc;

    /**
     * Initializes the wave function collapse algorithm with the tile shape's rules.
     * @param dimensions The dimensions of the 2d map.
     * @param shapes All the shapes that will be generated.
     */
    public WFCWrapperImpl(final Pair<Integer, Integer> dimensions, final Set<TileShape> shapes) {
        // TODO: fix initialization of the wfc object
        wfc = new WaveFunctionCollapseImpl(dimensions, Set.of());
    }

    @Override
    public final void setStaticTile(final Pair<Integer, Integer> position, final Tile tile) {
        // TODO: complete method
    }

    @Override
    public final void runWFC() {
        wfc.generateShapeMap();
    }

    @Override
    public final Tile getTileAt(final Pair<Integer, Integer> position) {
        // TODO: complete method
        return null;
    }
}
