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
     * @param relativePath path of json file relative to {@code JsonParser.ROOT}
     */
    public JsonLoader(final String relativePath) {
        this.parser = new JsonParserImpl(JsonParser.ROOT + "/" + relativePath);
    }

}
