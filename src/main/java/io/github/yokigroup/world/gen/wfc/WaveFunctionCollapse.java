package io.github.yokigroup.world.gen;

/**
 * The interface for the Wave Function Collapse algorithm.
 * It takes in a Set of shapes and the dimensions of the map, it
 * generates a Map of shapes for our map generation.
 */
public interface WaveFunctionCollapse {
    /**
     * The shapes allowed and their opposites.
     */
    enum Shape {
        UP(1),
        DOWN(0),
        LEFT(3),
        RIGHT(2);

        private final Shape connector;

        /**
         *
         * @return The opposite direction.
         */
        public Shape getConnection() {
            return this.connector;
        }

        /**
         *
         * @param connector the index of the opposite direction of the shape.
         */
        Shape(final int connector) {
            this.connector = Shape.values()[connector];
        }
    }


}
