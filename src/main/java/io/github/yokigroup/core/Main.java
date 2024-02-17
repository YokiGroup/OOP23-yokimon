package io.github.yokigroup.core;

import io.github.yokigroup.view.App;
import io.github.yokigroup.view.GameWindow;
import io.github.yokigroup.view.observer.JavaFXModelObserver;

public class Main {
    private final GameWindow gameWindow = new App();
    //private final GameLogicImpl model = new GameLogicImpl();

    public static void main(final String[] args){
        JavaFXModelObserver modelObs = new JavaFXModelObserver();
        Main mainClass = new Main();
        mainClass.gameWindow.create(args);
    }
}
