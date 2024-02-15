package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.gen.TileDirections;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.gen.TileShapeImpl;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Loads all the tiles contained in the json file describing them and puts them in a Set of {@link TileShape}s.
 */
public class TileShapeLoader extends JsonLoader<TileShape> {
    private static final String TILE_JSON_RPATH = "tiles.json";
    private static final String TILE_SHAPE_JPATHF = "$.%d.shape[*]";
    private Map<Set<TileDirections>, Set<Tile>> tiles = new HashMap<>();
    private final JsonParser parser = getParser();
    private final TileLoader tileLoader;

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
    public TileShapeLoader(TileLoader loader) {
        super(TILE_JSON_RPATH);
        Objects.requireNonNull(loader);
        this.tileLoader = loader;
    }

    private Set<TileDirections> convertToTileShapeSet(final Set<String> rawInput) {
        Set<TileDirections> retSet = new HashSet<>();
        rawInput.forEach(s -> retSet.add(TileDirections.valueOf(s)));
        return retSet;
    }

    private Set<TileDirections> getTileDirs(final int id) {
        List<String> rawTileDirs = parser.read(String.format(TILE_SHAPE_JPATHF, id));
        Set<TileDirections> tileDirs = new HashSet<>();

        rawTileDirs.forEach(d -> tileDirs.add(TileDirections.valueOf(d)));
        return tileDirs;
    }

    private Pair<Set<TileDirections>, Tile> load(final int id) {
        Set<TileDirections> tileDirs = getTileDirs(id);
        Tile tile = tileLoader.load(id);
        return new Pair<>(tileDirs, tile);
    }

    private void insertTile(final Set<TileDirections> dirs, final Tile tile) {
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
