package io.github.yokigroup.view;

import io.github.yokigroup.controller.Controller;
import io.github.yokigroup.controller.KeyEventType;
import io.github.yokigroup.core.GameLogicImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.GameEndSubmodule;
import io.github.yokigroup.event.submodule.InputSubmodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.render.painter.CanvasPainter;
import io.github.yokigroup.view.render.painter.Painter;
import io.github.yokigroup.view.render.observer.ModelObserverImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 * Main JavaFX Application entry.
 */
public final class GameFX extends Application {
    private static final String ROOT_RESOUCE_PATH = "io/github/yokigroup/view/";

    /**
     * The size of the current screen.
     */
    public static final Rectangle2D SCREEN_SIZE = Screen.getPrimary().getBounds();

    private record GameWindowResizeListener<T>(Scene gameScene, Canvas gameCanvas, StackPane stackPane,
                                               double ratio) implements ChangeListener<T> {
        @Override
            public void changed(final ObservableValue<? extends T> observableValue, final T t, final T t1) {
                final double paneWidth = gameScene.getWidth();
                final double paneHeight = gameScene.getHeight();
                final double currentRatio = paneWidth / paneHeight;
                double newHeight, newWidth;

                if (currentRatio > ratio) { // width has to be truncated
                    newHeight = paneHeight;
                    newWidth = paneHeight * ratio;
                } else { // height has to be truncated
                    newHeight = paneWidth / ratio;
                    newWidth = paneWidth;
                }
                gameCanvas.setWidth(newWidth);
                gameCanvas.setHeight(newHeight);
                stackPane.setMinSize(newWidth, newHeight);
                stackPane.setPrefSize(newWidth, newHeight);
                stackPane.setMaxSize(newWidth, newHeight);
            }
        }

    @Override
    public void start(final Stage stage) throws Exception {
        final double scaledY = SCREEN_SIZE.getHeight() * 2.0 / 3.0;
        final double ratio = 16.0 / 9.0; // 16:9 ratio
        final Dimension2D windowDim = new Dimension2D(scaledY * ratio, scaledY);

        final BorderPane rootElem = FXMLLoader.load(ClassLoader.getSystemResource(ROOT_RESOUCE_PATH + "game.fxml"));
        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());

        final StackPane stackPane = (StackPane) rootElem.getCenter();
        final List<Node> stackPaneList = stackPane.getChildren();
        final Canvas gameCanvas = (Canvas) stackPaneList.get(0);

        final BorderPane borderPane = (BorderPane) stackPaneList.get(1);
        final AnchorPane anchorPane = (AnchorPane) borderPane.getTop();
        final Label eventLabel = (Label) borderPane.getBottom();
        final Label playerYokimonLabel = (Label) anchorPane.getChildren().stream()
                .filter(n -> "playerYokimonLabel".equals(n.getId())).findFirst().orElseThrow();
        final Label enemyYokimonLabel = (Label) anchorPane.getChildren().stream()
                .filter(n -> "enemyYokimonLabel".equals(n.getId())).findFirst().orElseThrow();

        final Painter painter = new CanvasPainter(gameCanvas
                .getGraphicsContext2D(), eventLabel, playerYokimonLabel, enemyYokimonLabel);

        final ModelObserverImpl modelObs = new ModelObserverImpl(painter);
        final GameLogicImpl gameThread = new GameLogicImpl(modelObs, painter, Platform::exit);
        final MessageHandler handler = gameThread.getMessageHandler();
        final Publisher<Pair<KeyEventType, String>> keyEventPub = new PublisherImpl<>();
        keyEventPub.addObserver(gameThread.getController());

        scene.setOnKeyPressed(
                event -> keyEventPub.notifyObservers(new Pair<>(KeyEventType.PRESS, event.getText()))
        );
        scene.setOnKeyReleased(
                event -> keyEventPub.notifyObservers(new Pair<>(KeyEventType.RELEASE, event.getText()))
        );
        gameThread.start();

        final GameWindowResizeListener<Object> resizeListener =
                new GameWindowResizeListener<>(scene, gameCanvas, stackPane, ratio);
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        stage.fullScreenProperty().addListener(resizeListener);
        stage.maximizedProperty().addListener(resizeListener);

        stage.setOnCloseRequest(a -> handler.handle(GameEndSubmodule.class, GameEndSubmodule::killGame));

        final InputStream iconStream = ClassLoader
                .getSystemResourceAsStream(ROOT_RESOUCE_PATH + "icon.png");
        if (iconStream != null) {
            stage.getIcons().add(new Image(iconStream));
        }
        stage.setTitle("Yokimon");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Runs this view.
     *
     * @param args String[]
     */
    public static void run(final String[] args) {
        launch(args);
    }
}
