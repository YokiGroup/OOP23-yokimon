package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.util.json.JsonParser;

public class YokimonLoader extends AbstractJsonLoader<YokimonImpl>{
    // path of yokimon json file relative to JsonParser.ROOT
    private static final String RELYOKIMONPATH = "yokimons.json";

    public YokimonLoader() {
        super(JsonParser.ROOT+"/"+RELYOKIMONPATH);
    }

    @Override
    public YokimonImpl load(int id) {
        return null;
    }
}
