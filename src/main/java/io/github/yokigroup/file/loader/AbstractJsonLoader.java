package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;
import io.github.yokigroup.util.json.PathNotFoundException;

import java.util.HashMap;
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

    public Map<Integer, T> getAll(){
        Map<Integer, T> retMap = new HashMap<>();
        int i = 1;
        try {
            // Will stop once it finds no more items to load without breaking id continuity.
            while (true) {
                retMap.put(i, load(i));
                i++;
            }
        }catch(PathNotFoundException e) {
            return retMap;
        }
    }
}
