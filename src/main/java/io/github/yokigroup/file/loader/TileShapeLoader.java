package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.gen.TileShapeImpl;
import io.github.yokigroup.world.tile.TileBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Loads all the tiles contained in the json file describing them and puts them in a Set of {@link TileShape}s.
 */
public class TileShapeLoader extends JsonLoader<TileShape> {
    private static final String TILE_JSON_RPATH = "tiles.json";
    private Map<Set<Direction>, Set<TileBuilder>> tiles = new HashMap<>();
    private final JsonParser parser = getParser();
    private final IdJsonLoader<TileBuilder> tileLoader;

    /**
     * Constructor.
     */
    public TileShapeLoader() {
        this(new TileLoader());
    }

    /**
     * Instance TileShapeLoader with an existing TileLoader.
     * @param loader loader to use
     */
    public TileShapeLoader(final TileLoader loader) {
        super(TILE_JSON_RPATH);
        Objects.requireNonNull(loader);
        this.tileLoader = loader;
    }

    private Set<Direction> convertToTileShapeSet(final Set<String> rawInput) {
        Set<Direction> retSet = new HashSet<>();
        rawInput.forEach(s -> retSet.add(Direction.valueOf(s)));
        return retSet;
    }

    private Pair<Set<Direction>, TileBuilder> load(final int id) {
        TileBuilder tileBuilder = tileLoader.load(id);
        return new Pair<>(tileBuilder.getAdjacencies(), tileBuilder);
    }

    private void insertTile(final Set<Direction> dirs, final TileBuilder tile) {
        Objects.requireNonNull(dirs);
        Objects.requireNonNull(tile);

        if (!tiles.containsKey(dirs)) {
            tiles.put(dirs, new HashSet<>());
        }
        tiles.get(dirs).add(tile);
    }

    /**
     * @return Set of all the possible {@link TileShape}s, each {@link TileShape} containing all tiles of that shape.
     */
    public Set<TileShape> getAll() {
        Set<TileShape> shapes = new HashSet<>();

        if (tiles.isEmpty()) {
            doUntilPathException(null, (c, i) -> {
                var nextTile = load(i + 1);
                insertTile(nextTile.x(), nextTile.y());
            });
        }

        for (var tileEntry: tiles.entrySet()) {
            shapes.add(new TileShapeImpl(tileEntry.getValue(), tileEntry.getKey()));
        }

        return shapes;
    }
}
