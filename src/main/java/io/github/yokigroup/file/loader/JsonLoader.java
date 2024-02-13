package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;
import io.github.yokigroup.util.json.PathNotFoundException;

import java.util.function.BiFunction;

/**
 * @param <T>
 */
public abstract class JsonLoader<T> {
    private final JsonParser parser;

    protected <T> T doUntilPathException(BiFunction<T, Integer, T> fun) {
        T aggregator = null;
        int i = 1;

        try {
            while(true) {
                aggregator = fun.apply(aggregator, i);
                i++;
            }
        }catch(PathNotFoundException ignored) { }

        return aggregator;
    }

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
