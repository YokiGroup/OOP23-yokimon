package io.github.yokigroup.file.loader;

import io.github.yokigroup.world.gen.TileShape;

import java.util.Set;

public class TileShapeLoader extends AbstractJsonLoader<TileShape> {
    private static final String TILEJSONRPATH = "skills.json";

    public TileShapeLoader() {
        super(TILEJSONRPATH);
    }

    @Override
    public TileShape load(int id) {
        return null;
    }
}
