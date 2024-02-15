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

    private class GameWindowResizeListener implements ChangeListener<Number> {
        private final double ratio;
        private final Pane gamePane;


        public GameWindowResizeListener(Pane gamePane, final double ratio) {
            this.ratio = ratio;
            this.gamePane = gamePane;
        }

        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
            final double paneWidth = gamePane.getWidth();
            final double paneHeight = gamePane.getHeight();
            final double currentRatio = paneWidth / paneHeight;

            if (currentRatio > ratio) { // width has to be truncated
                System.err.println("width is too big");
                gamePane.setStyle("-fx-background-color: blue");
                gamePane.setPrefWidth(paneHeight * ratio);
                gamePane.setMaxWidth(paneHeight * ratio);
            } else { // height has to be truncated
                System.err.println("height is too big");
                gamePane.setStyle("-fx-background-color: red");
                gamePane.setPrefHeight(paneWidth / ratio);
                gamePane.setMaxHeight(paneWidth / ratio);
            }
        }
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final double scaledY = screenSize.getWidth()*2.0/3.0;
        final double ratio = 16.0/9.0; // 16:9 ratio
        final Dimension2D windowDim = new Dimension2D(scaledY*ratio, scaledY);

        final BorderPane rootElem = FXMLLoader.load(ClassLoader.getSystemResource("view/game/test.fxml"));
        final Pane gamePane = (Pane) rootElem.getCenter(); // FIXME maybe casting like this isn't the smartest choice
        final GameWindowResizeListener resizeListener = new GameWindowResizeListener(gamePane, ratio);
        gamePane.widthProperty().addListener(resizeListener);
        gamePane.heightProperty().addListener(resizeListener);

        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());
        stage.setTitle("Yokimon");
        stage.setScene(scene);
        stage.show();
    }

    public static void run(final String[] args) {
        launch(args);
    }
}
