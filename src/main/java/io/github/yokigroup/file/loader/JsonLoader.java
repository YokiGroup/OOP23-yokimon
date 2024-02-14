package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;
import io.github.yokigroup.util.json.PathNotFoundException;

import java.util.function.BiConsumer;

/**
 * @param <T>
 */
public abstract class JsonLoader<T> {
    private final JsonParser parser;

    protected final <T> T doUntilPathException(T aggregator, BiConsumer<T, Integer> fun) {
        int i = 0;

        try {
            while(true) {
                fun.accept(aggregator, i);
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
