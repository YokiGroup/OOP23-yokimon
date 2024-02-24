package io.github.yokigroup.core;

import io.github.yokigroup.view.App;
import io.github.yokigroup.view.GameWindow;

/**
 * The main class to run the program with.
 */
final class Main {
    final private GameWindow gameWindow = new App();

    private Main() {
    }

    /**
     * Entry point of the program.
     * @param args arguments passed to the program
     */
    public static void main(final String[] args) {
        Main mainClass = new Main();
        mainClass.gameWindow.create(args);
    }
}
