package io.github.yokigroup.view;

import io.github.yokigroup.core.GameLogicImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.InputSubmodule;
import io.github.yokigroup.view.observer.ModelObserverImpl;
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

import java.util.function.Consumer;

/**
 * Main JavaFX Application entry.
 */
public class GameFX extends Application {
    public final static Rectangle2D screenSize = Screen.getPrimary().getBounds();

    private static class GameWindowResizeListener<T> implements ChangeListener<T> {
        private final double ratio;
        private final Canvas gameCanvas;
        private final Scene gameScene;
        private final Painter painter;


        public GameWindowResizeListener(final Scene gameScene, final Canvas gameCanvas, Painter painter, final double ratio) {
            this.ratio = ratio;
            this.gameCanvas = gameCanvas;
            this.gameScene = gameScene;
            this.painter = painter;
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

            painter.repaint();
//            var gc = gameCanvas.getGraphicsContext2D();
//            gc.setFill(Color.BLUE);
//            gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        }
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final double scaledY = screenSize.getHeight() * 2.0 / 3.0;
        final double ratio = 16.0/9.0; // 16:9 ratio
        final Dimension2D windowDim = new Dimension2D(scaledY*ratio, scaledY);

        final BorderPane rootElem = FXMLLoader.load(ClassLoader.getSystemResource("view/game/test.fxml"));
        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());

        final Canvas gameCanvas = (Canvas) rootElem.getCenter(); // FIXME maybe casting like this isn't the smartest choice
        Painter painter = new CanvasPainter(gameCanvas.getGraphicsContext2D());

        ModelObserverImpl modelObs = new ModelObserverImpl(painter);
        GameLogicImpl gameThread = new GameLogicImpl(modelObs);
        MessageHandler handler = gameThread.getMessageHandler();

        scene.setOnKeyPressed(
                event -> handler.handle(InputSubmodule.class,
                        (Consumer<InputSubmodule>) s -> s.registerKeyPress(event.getText())
                )
        );
        scene.setOnKeyReleased(
                event -> handler.handle(InputSubmodule.class,
                        (Consumer<InputSubmodule>) s -> s.registerKeyRelease(event.getText())
                )
        );
        gameThread.start();

        final GameWindowResizeListener<Object> resizeListener = new GameWindowResizeListener<>(scene, gameCanvas, painter, ratio);
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        stage.fullScreenProperty().addListener(resizeListener);
        stage.maximizedProperty().addListener(resizeListener);

        stage.setTitle("Yokimon");
        stage.setScene(scene);
        stage.show();
    }

    public static void run(final String[] args) {
        launch(args);
    }
}
