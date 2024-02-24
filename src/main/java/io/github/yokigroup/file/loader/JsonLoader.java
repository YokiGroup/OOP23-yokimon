package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;
import io.github.yokigroup.util.json.PathNotFoundException;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Base class used for instantiating objects from the json files they are contained in.
 * @param <T> type of object produced by the loader
 */
class JsonLoader<T> {
    private final JsonParser parser;

    /**
     * Performs BiConsumer {@param fun} until it encounters a {@link PathNotFoundException}. Useful in parser calls.
     * @param aggregator aggregator passed in every
     * @param fun function to invoke: First Parameter is the {@param aggregator} (list, set, map...), \
     *            second parameter is an index.
     * @return aggregator after {@param fun} has been run
     * @param <T> aggregator type
     */
    protected final <T> T doUntilPathException(final T aggregator, final BiConsumer<T, Integer> fun) {
        int i = 0;

        try {
            while (true) {
                fun.accept(aggregator, i);
                i++;
            }
        } catch (PathNotFoundException ignored) { }

        return aggregator;
    }

    /**
     * @return the instanced parser
     */
    protected final JsonParser getParser() {
        return parser;
    }

    /**
     * Read a vector object (a json object containing x and y coordinates) from path.
     * @param path jpath expression representing the object to read
     * @return parsed vector
     */
    protected final Vector2 getVector2(final String path) {
        final Map<String, Double> rawVec = parser.read(path);
        return new Vector2Impl(rawVec.get("x"), rawVec.get("y"));
    }

    /**
     * @param relativePath path of json file relative to {@code JsonParser.ROOT}
     */
    public JsonLoader(final String relativePath) throws IOException {
        this.parser = new JsonParserImpl(JsonParser.ROOT + relativePath);
    }

}
