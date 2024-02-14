package io.github.yokigroup.util.json;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.Objects;

/**
 * Implements JsonParser by wrapping the jsonPath library.
 */
public final class JsonParserImpl implements JsonParser {
    private final DocumentContext doc;

    /**
     * Constructs a JsonParseImpl.
     * @param resourcePath file to open and parse, located on the classpath by the SystemClassLoader
     */
    public JsonParserImpl(final String resourcePath) {
        Objects.requireNonNull(resourcePath);
        try (var is = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath)) {
            doc = JsonPath.parse(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T read(final String jsonPath) {
        Objects.requireNonNull(jsonPath);
        JsonPath path = JsonPath.compile(jsonPath);
        try{
            return doc.read(path);
        }catch(com.jayway.jsonpath.JsonPathException e){
            throw new PathNotFoundException(e);
        }
    }
}
