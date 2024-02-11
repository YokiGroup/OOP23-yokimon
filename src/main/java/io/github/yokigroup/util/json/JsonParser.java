package io.github.yokigroup.util.json;

/**
 * Parses a json document following the JSONPath standard.
 * @see <a href="https://datatracker.ietf.org/wg/jsonpath/about/">jsonpath</a>
 */
public interface JsonParser {
    /**
     * Root dir of json files contained in the ClassPath.
     */
    String ROOT = "io/github/yokigroup/util/json";

    /**
     * @param jsonPath json path expression to evaluate
     * @return value returned by parse cast to type {@param T}
     * @param <T>
     */
    <T> T read(String jsonPath);
}
