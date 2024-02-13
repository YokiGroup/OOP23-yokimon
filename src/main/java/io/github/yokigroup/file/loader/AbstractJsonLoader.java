package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 */
public abstract class AbstractJsonLoader<T> {
    private final JsonParser parser;

    protected JsonParser getParser(){
        return parser;
    }

    /**
     * @param relativePath path of json file relative to {@code JsonParser.ROOT}
     */
    public AbstractJsonLoader(final String relativePath){
        this.parser = new JsonParserImpl(JsonParser.ROOT+"/"+relativePath);
    }

    public abstract T load(int id);
}
