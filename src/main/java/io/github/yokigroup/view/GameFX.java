package io.github.yokigroup.view;

import io.github.yokigroup.core.GameLogicImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.InputSubmodule;
import io.github.yokigroup.view.render.painter.CanvasPainter;
import io.github.yokigroup.view.render.painter.Painter;
import io.github.yokigroup.view.render.observer.ModelObserverImpl;
import javafx.application.Application;
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

    private static class GameWindowResizeListener<T> implements ChangeListener<T> {
        private final double ratio;
        private final Canvas gameCanvas;
        private final Stage gameStage;
        private final StackPane stackPane;


        GameWindowResizeListener(final Stage gameStage, final Canvas gameCanvas, final StackPane stackPane, final double ratio) {
            this.ratio = ratio;
            this.gameCanvas = gameCanvas;
            this.gameStage = gameStage;
            this.stackPane = stackPane;
        }

        @Override
        public void changed(final ObservableValue<? extends T> observableValue, final T t, final T t1) {
            final double paneWidth = gameStage.getWidth();
            final double paneHeight = gameStage.getHeight();
            final double currentRatio = paneWidth / paneHeight;
            double newHeight, newWidth;

            System.out.printf("%f %f AAAAAAAAAAAAAAAAAA\n%n", paneWidth, paneHeight);
            if (currentRatio > ratio) { // width has to be truncated
                newHeight = paneHeight;
                newWidth = paneHeight * ratio;
            } else { // height has to be truncated
                newHeight = paneWidth / ratio;
                newWidth = paneWidth;
            }
            gameCanvas.minWidth(newWidth);
            gameCanvas.minHeight(newHeight);
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

        final BorderPane rootElem = FXMLLoader.load(ClassLoader.getSystemResource(ROOT_RESOUCE_PATH + "test.fxml"));
        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());

        final StackPane stackPane = (StackPane) rootElem.getCenter();
        final List<Node> stackPaneList = stackPane.getChildren();
        final Canvas gameCanvas = (Canvas) stackPaneList.get(0); // FIXME maybe casting like this isn't the smartest choice

        final BorderPane borderPane = (BorderPane) stackPaneList.get(1);
        final AnchorPane anchorPane = (AnchorPane) borderPane.getTop();
        final Label eventLabel = (Label) borderPane.getBottom();
        final Label playerYokimonLabel = (Label) anchorPane.getChildren().stream()
                .filter(n -> n.getId().equals("playerYokimonLabel")).findFirst().orElseThrow();
        final Label enemyYokimonLabel = (Label) anchorPane.getChildren().stream()
                .filter(n -> n.getId().equals("enemyYokimonLabel")).findFirst().orElseThrow();

        final Painter painter = new CanvasPainter(gameCanvas
                .getGraphicsContext2D(), eventLabel, playerYokimonLabel, enemyYokimonLabel);

        final ModelObserverImpl modelObs = new ModelObserverImpl(painter);
        final GameLogicImpl gameThread = new GameLogicImpl(modelObs, painter);
        final MessageHandler handler = gameThread.getMessageHandler();

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

        final GameWindowResizeListener<Object> resizeListener =
                new GameWindowResizeListener<>(stage, gameCanvas, stackPane, ratio);
        stage.widthProperty().addListener(resizeListener);
        stage.heightProperty().addListener(resizeListener);
        stage.fullScreenProperty().addListener(resizeListener);
        stage.maximizedProperty().addListener(resizeListener);

        stage.setOnCloseRequest(a -> gameThread.stopGame());

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
