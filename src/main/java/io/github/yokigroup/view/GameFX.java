package io.github.yokigroup.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.event.DocumentEvent;

public class GameFX extends Application {
    public final static Rectangle2D screenSize = Screen.getPrimary().getBounds();

    @Override
    public void start(final Stage stage) throws Exception {
        final double scaledY = screenSize.getWidth()*2.0/3.0;
        final double ratio = 16.0/9.0; // 16:9 ratio
        final Dimension2D windowDim = new Dimension2D(scaledY*ratio, scaledY);

        final Pane rootElem = FXMLLoader.load(ClassLoader.getSystemResource("view/game/test.fxml"));

        final Scene scene = new Scene(rootElem, windowDim.getWidth(), windowDim.getHeight());
        stage.setTitle("Yokimon");
        stage.setScene(scene);
        stage.show();
    }

    public static void run(final String[] args) {
        launch(args);
    }
}
