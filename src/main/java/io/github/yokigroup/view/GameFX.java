package io.github.yokigroup.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameFX extends Application {
    public final static Rectangle2D screenSize = Screen.getPrimary().getBounds();

    private static class GameWindowResizeListener<T> implements ChangeListener<T> {
        private final double ratio;
        private final Pane gamePane;
        private final Scene gameScene;


        public GameWindowResizeListener(Scene gameScene, Pane gamePane, final double ratio) {
            this.ratio = ratio;
            this.gamePane = gamePane;
            this.gameScene = gameScene;
        }

        @Override
        public void changed(ObservableValue<? extends T> observableValue, T t, T t1) {
            double paneWidth = gamePane.getWidth();
            double paneHeight = gamePane.getHeight();
            final double currentRatio = paneWidth / paneHeight;

            if (currentRatio > ratio) { // width has to be truncated
                paneHeight = gameScene.getHeight();
                gamePane.setPrefHeight(paneHeight);
                gamePane.setMaxHeight(paneHeight);
                gamePane.setPrefWidth(paneHeight * ratio);
                gamePane.setMaxWidth(paneHeight * ratio);
            } else { // height has to be truncated
                paneWidth = gameScene.getWidth();
                gamePane.setPrefWidth(paneWidth);
                gamePane.setMaxWidth(paneWidth);
                gamePane.setPrefHeight(paneWidth / ratio);
                gamePane.setMaxHeight(paneWidth / ratio);
            }
        }
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final double scaledY = screenSize.getWidth() * 2.0 / 3.0;
        final double ratio = 16.0/9.0; // 16:9 ratio
        final Dimension2D windowDim = new Dimension2D(scaledY*ratio, scaledY);

        final BorderPane rootElem = FXMLLoader.load(ClassLoader.getSystemResource("view/game/test.fxml"));
        final Pane gamePane = (Pane) rootElem.getCenter(); // FIXME maybe casting like this isn't the smartest choice
        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());

        final GameWindowResizeListener<Object> resizeListener = new GameWindowResizeListener<>(scene, gamePane, ratio);
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
