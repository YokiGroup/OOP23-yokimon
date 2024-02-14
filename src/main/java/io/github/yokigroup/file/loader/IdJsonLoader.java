package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.json.PathNotFoundException;

import java.util.HashMap;
import java.util.Map;

public abstract class IdJsonLoader<T> extends JsonLoader<T> {
    /**
     * @param relativePath path of json file relative to {@code JsonParser.ROOT}
     */
    public IdJsonLoader(String relativePath) {
        super(relativePath);
    }

    public abstract T load(int id);

    public Map<Integer, T> getAll(){
        Map<Integer, T> retMap = new HashMap<>();
        return doUntilPathException(new HashMap<>(), (c, i) -> {
            c.put(i+1, load(i+1));
        });
    }
}
