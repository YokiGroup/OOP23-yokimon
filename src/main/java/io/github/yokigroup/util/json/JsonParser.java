package io.github.yokigroup.util.json;

/**
 * Parses a json document following the JSONPath standard.
 * @see <a href="https://datatracker.ietf.org/wg/jsonpath/about/">jsonpath</a>
 */
public interface JsonParser {
    <T> T read(String jsonPath);
}
