package io.github.yokigroup.core;

import io.github.yokigroup.view.App;
import io.github.yokigroup.view.GameWindow;

/**
 * The main class to run the program.
 */
public class Main {
    private final GameWindow gameWindow = new App();
    //private final GameLogicImpl model = new GameLogicImpl();

    public static void main(final String[] args) {
        final Main mainClass = new Main();
        mainClass.gameWindow.create(args);
    }
}
