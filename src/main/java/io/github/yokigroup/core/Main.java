package io.github.yokigroup.core;

import io.github.yokigroup.view.App;
import io.github.yokigroup.view.GameWindow;

/**
 * The main class to run the program.
 */
public class Main {
    private final GameWindow gameWindow = new App();
    //private final GameLogicImpl model = new GameLogicImpl();

    /**
     * Launch the game.
     * @param args ...
     */
    public static void main(final String[] args) {
        Main mainClass = new Main();
        mainClass.gameWindow.create(args);
    }
}
