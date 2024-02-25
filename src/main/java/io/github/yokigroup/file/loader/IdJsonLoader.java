package io.github.yokigroup.file.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JsonLoader with the added functionality of loading an object by id, or loading all elements.
 * @param <T> type of class being loaded from a json file.
 */
public abstract class IdJsonLoader<T> extends JsonLoader<T> {
    /**
     * @param relativePath path of json file relative to {@code JsonParser.ROOT}
     * @throws IOException File failed to open
     */
    public IdJsonLoader(final String relativePath) throws IOException {
        super(relativePath);
    }

    /**
     * Loads the class with id {@param id}.
     * @param id id of the class to load
     * @return class of id {@param id} loaded from the json file containing it
     */
    public abstract T load(int id);

    /**
     * Loads all the classes contained in the json file.
     * @return classes loaded from the json file represented in a map of entries (id, instance)
     */
    public Map<Integer, T> getAll() {
        return doUntilPathException(new HashMap<>(), (c, i) -> {
            c.put(i + 1, load(i + 1));
        });
    }
}
