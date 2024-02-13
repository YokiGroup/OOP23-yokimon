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
