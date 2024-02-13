package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;
import io.github.yokigroup.util.json.PathNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @param <T>
 */
public abstract class JsonLoader<T> {
    private final JsonParser parser;

    protected JsonParser getParser(){
        return parser;
    }

    /**
     * @param relativePath path of json file relative to {@code JsonParser.ROOT}
     */
    public JsonLoader(final String relativePath){
        this.parser = new JsonParserImpl(JsonParser.ROOT+"/"+relativePath);
    }

}
