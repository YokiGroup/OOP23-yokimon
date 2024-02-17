package io.github.yokigroup.view;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.observer.JavaFXModelObserver;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Main JavaFX Application entry.
 */
public class GameFX extends Application {
    public final static Rectangle2D screenSize = Screen.getPrimary().getBounds();

    private static class GameWindowResizeListener<T> implements ChangeListener<T> {
        private final double ratio;
        private final Canvas gameCanvas;
        private final Scene gameScene;


        public GameWindowResizeListener(final Scene gameScene, final Canvas gameCanvas, final double ratio) {
            this.ratio = ratio;
            this.gameCanvas = gameCanvas;
            this.gameScene = gameScene;
        }

        @Override
        public void changed(final ObservableValue<? extends T> observableValue, final T t, final T t1) {
            final double paneWidth = gameScene.getWidth();
            final double paneHeight = gameScene.getHeight();
            final double currentRatio = paneWidth / paneHeight;

            if (currentRatio > ratio) { // width has to be truncated
                gameCanvas.setHeight(paneHeight);
                gameCanvas.setWidth(paneHeight * ratio);
            } else { // height has to be truncated
                gameCanvas.setWidth(paneWidth);
                gameCanvas.setHeight(paneWidth / ratio);
            }

//            var gc = gameCanvas.getGraphicsContext2D();
//            gc.setFill(Color.BLUE);
//            gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        }
    }

    @Override
    public void start(final Stage stage) throws Exception {
        JavaFXModelObserver modelObs = new JavaFXModelObserver();
        GameMessageHandler handler = new GameMessageHandler(modelObs);

        final double scaledY = screenSize.getHeight() * 2.0 / 3.0;
        final double ratio = 16.0/9.0; // 16:9 ratio
        final Dimension2D windowDim = new Dimension2D(scaledY*ratio, scaledY);

        final BorderPane rootElem = FXMLLoader.load(ClassLoader.getSystemResource("view/game/test.fxml"));
        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());

        final Canvas gameCanvas = (Canvas) rootElem.getCenter(); // FIXME maybe casting like this isn't the smartest choice
        modelObs.init(gameCanvas.getGraphicsContext2D());

        final GameWindowResizeListener<Object> resizeListener = new GameWindowResizeListener<>(scene, gameCanvas, ratio);
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        stage.fullScreenProperty().addListener(resizeListener);
        stage.maximizedProperty().addListener(resizeListener);

        stage.setTitle("Yokimon");
        stage.setScene(scene);
        stage.show();
        handler.handle(PlayerCharacterSubmodule.class, s -> {
            s.movePlayerBy(new Vector2Impl(0, 0));
        });
    }

    public static void run(final String[] args) {
        launch(args);
    }
}
