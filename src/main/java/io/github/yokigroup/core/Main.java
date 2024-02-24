package io.github.yokigroup.core;

import io.github.yokigroup.view.App;
import io.github.yokigroup.view.GameWindow;

/**
 * The main class to run the program.
 */
public class Main {

    public static void main(final String[] args) {
        final GameWindow gameWindow = new App();
        gameWindow.create(args);
    }
}
