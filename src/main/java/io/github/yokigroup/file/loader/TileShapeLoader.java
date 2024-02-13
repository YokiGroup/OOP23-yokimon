package io.github.yokigroup.file.loader;

import com.google.common.collect.ImmutableList;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.PathNotFoundException;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.gen.TileShapeImpl;
import io.github.yokigroup.world.tile.Tile;

import java.util.*;

public class TileShapeLoader extends JsonLoader<TileShape>{
    private static final String TILEJSONRPATH = "tiles.json";
    //private static final String
    private Map<Set<TileShape.TileDirections>, Set<Tile>> tiles = new HashMap<>();

    public TileShapeLoader() {
        super(TILEJSONRPATH);
    }

    private Pair<Set<TileShape.TileDirections>, Tile> load(int id){
        JsonParser parser = getParser();
        Set<TileShape.TileDirections> tileDirs;
        Tile tile;


        return null;
    }

    private void insertTile(Set<TileShape.TileDirections> dirs, Tile tile) {
        Objects.requireNonNull(dirs);
        Objects.requireNonNull(tile);

        if(!tiles.containsKey(dirs)) {
            tiles.put(dirs, new HashSet<>());
        }
        tiles.get(dirs).add(tile);
    }

    public Set<TileShape> getAll() {
        Set<TileShape> shapes = new HashSet<>();
        int i = 1;

        if(tiles.isEmpty()){
            try {
                while(true) {
                    var nextTile = load(i);
                    insertTile(nextTile.x(), nextTile.y());
                    i++;
                }
            }catch(PathNotFoundException ignored){ }
        }

        for(var tileEntry: tiles.entrySet()) {
            shapes.add(new TileShapeImpl(tileEntry.getValue(), tileEntry.getKey()));
        }

        return shapes;
    }
}
