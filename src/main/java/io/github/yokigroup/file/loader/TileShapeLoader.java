package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.PathNotFoundException;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.tile.Tile;

import java.util.HashSet;
import java.util.Set;

public class TileShapeLoader extends JsonLoader<TileShape>{
    private static final String TILEJSONRPATH = "tiles.json";
    //private static final String

    public TileShapeLoader() {
        super(TILEJSONRPATH);
    }

    private Pair<Set<TileShape.TileDirections>, Tile> load(int id){
        JsonParser parser = getParser();
        Set<TileShape.TileDirections> tileDirs;
        Tile tile;


        return null;
    }

    public Set<TileShape> getAll(){
        Set<TileShape> shapes = new HashSet<>();
        int i = 1;

        try{
            while(true){

                i++;
            }
        }catch(PathNotFoundException e){

        }

        return shapes;
    }
}
