package io.github.yokigroup.file.loader;

import com.google.common.collect.ImmutableList;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.PathNotFoundException;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.gen.TileShapeImpl;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileImpl;

import java.util.*;

public class TileShapeLoader extends JsonLoader<TileShape>{
    private static final String TILE_JSON_RPATH = "tiles.json";
    private static final String TILE_SHAPE_JPATHF = "$.%d.shape[*]";
    private static final String TILE_HITBOX_TYPE_JPATHF = "$.%d.hitboxes[%d]";
    private Map<Set<TileShape.TileDirections>, Set<Tile>> tiles = new HashMap<>();
    private final JsonParser parser = getParser();

    public TileShapeLoader() {
        super(TILE_JSON_RPATH);
    }

    private Set<TileShape.TileDirections> convertToTileShapeSet(final Set<String> rawInput) {
        Set<TileShape.TileDirections> retSet = new HashSet<>();
        rawInput.forEach(s -> retSet.add(TileShape.TileDirections.valueOf(s)));
        return retSet;
    }

    private Vector2 getVector2(final String path) {
        final Map<String, Double> rawVec = parser.read(path);
        return new Vector2Impl(rawVec.get("x"), rawVec.get("y"));
    }

    private Hitbox getHitbox(final int id, final int index) {
        final String formattedHitboxJPath = String.format(TILE_HITBOX_TYPE_JPATHF, id, index);
        final String type = parser.read(formattedHitboxJPath+".type");
        final Vector2 pos = getVector2(formattedHitboxJPath+".position");

        Hitbox retHBox;
        switch(type) {
            case "rect":
                final Vector2 dim = getVector2(formattedHitboxJPath+".dimensions");
                retHBox = new RectangularHitbox(pos, dim);
                break;

            case "circle":
                final double radius = parser.read(formattedHitboxJPath+".radius");
                retHBox = new CircularHitbox(pos, radius);
                break;

            default:
                throw new RuntimeException(String.format("invalid type of hitbox index %d of id %d: received %s", index, id, type));
        }
        return retHBox;
    }

    private <T> Set<T> ifNullReturnEmpty(Set<T> set){
        return set != null ? set : Set.of();
    }

    private Set<Hitbox> getHitboxes(final int id) {
        return ifNullReturnEmpty(doUntilPathException((c, i) -> {
            Set<Hitbox> coll = c;
            if(coll == null) coll = new HashSet<>();

            coll.add(getHitbox(id, i));
            return coll;
        }));
    }

    private Set<TileShape.TileDirections> getTileDirs(final int id) {
        List<String> rawTileDirs = parser.read(String.format(TILE_SHAPE_JPATHF, id));
        Set<TileShape.TileDirections> tileDirs = new HashSet<>();

        rawTileDirs.forEach(d -> tileDirs.add(TileShape.TileDirections.valueOf(d)));
        return tileDirs;
    }

    private Set<Vector2>  getSpawnPositions(final int id) {
        final String spawnPositionJPath = "$."+id+".spawns[%d]";
        return ifNullReturnEmpty(doUntilPathException((c, i) -> {
            Set<Vector2> aggr = c;
            if(aggr == null) aggr = new HashSet<>();

            aggr.add(getVector2(String.format(spawnPositionJPath, i)));
            return aggr;
        }));
    }

    private Pair<Set<TileShape.TileDirections>, Tile> load(final int id) {
        Set<TileShape.TileDirections> tileDirs = getTileDirs(id);
        Tile tile = new TileImpl(getHitboxes(id), getSpawnPositions(id));
        return new Pair<>(tileDirs, tile);
    }

    private void insertTile(final Set<TileShape.TileDirections> dirs, final Tile tile) {
        Objects.requireNonNull(dirs);
        Objects.requireNonNull(tile);

        if(!tiles.containsKey(dirs)) {
            tiles.put(dirs, new HashSet<>());
        }
        tiles.get(dirs).add(tile);
    }

    public Set<TileShape> getAll() {
        Set<TileShape> shapes = new HashSet<>();

        if(tiles.isEmpty()){
            doUntilPathException((c, i) -> {
                var nextTile = load(i+1);
                insertTile(nextTile.x(), nextTile.y());
                return null;
            });
        }

        for(var tileEntry: tiles.entrySet()) {
            shapes.add(new TileShapeImpl(tileEntry.getValue(), tileEntry.getKey()));
        }

        return shapes;
    }
}
