package io.github.yokigroup.view;

import io.github.yokigroup.battle.fight.Fight;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.InputStream;

public final class FightView extends Application {

    private static final int SCREEN_WIDTH = 850;
    private static final int SCREEN_HEIGTH = 500;
    private static final int HPBAR_LENGTH = 200;
    private static final int HPBAR_HEIGTH = 40;
    private static final int DELTA = 20;
    private static Pane root_draft = new Pane();
    private static final String BACKGROUND_URL = "https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/204364595/original/86db6005cd51b4f60e71cca277f603a82cf5646a/draw-a-pixel-pokemon-battle-background.png";
    private static final String BACKGROUND_PNG = "C:/Users/Marilia/Downloads/forest-background-5th-style-pass-portfolio-edit.png";

    @Override
    public void start(final Stage primaryStage) throws Exception {

        Scene scene = new Scene(root_draft, SCREEN_WIDTH, SCREEN_HEIGTH);

        //BACKGROUND        TODO MUST REPLACE URL WITH PNG
        BackgroundImage backgroundImage= new BackgroundImage(new Image(BACKGROUND_URL,SCREEN_WIDTH,SCREEN_HEIGTH,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root_draft.setBackground(new Background(backgroundImage));

        //HEALTHBARS
        final Rectangle healthbar1 = new Rectangle(HPBAR_LENGTH, HPBAR_HEIGTH, Color.DARKGREY);
        healthbar1.setX(DELTA);
        healthbar1.setY(DELTA);
        final Rectangle healthbar2 = new Rectangle(HPBAR_LENGTH, HPBAR_HEIGTH, Color.DARKGREY);
        healthbar2.setX(SCREEN_WIDTH - HPBAR_LENGTH - DELTA);
        healthbar2.setY(DELTA);
        root_draft.getChildren().add(healthbar1);
        root_draft.getChildren().add(healthbar2);


        primaryStage.setScene(scene);
        primaryStage.setTitle("Fight");
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch(args);
    }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            Application.launch(JavaFXApp.class, args);
            JavaFXApp.run(args);
        }

        //TODO COMPLETE THIS AND DEBUG
        public void drawHPbars(final Fight fight) {

            Rectangle healthbarMyYokimon = new Rectangle(200.0, 50.0, Color.GREY);
            Rectangle healthbarOppYokimon = new Rectangle(200.0, 50.0, Color.GREY);

            DoubleProperty healthPercentage1 = new SimpleDoubleProperty(fight.getHPPercentage(fight.getCurrentMyYokimon()));
            DoubleProperty healthPercentage2 = new SimpleDoubleProperty(fight.getHPPercentage(fight.getCurrentOpponent()));

            DoubleBinding b1 = healthbarMyYokimon.widthProperty().multiply(healthPercentage1);
            DoubleBinding b2 = healthbarOppYokimon.widthProperty().multiply(healthPercentage2);
            healthbarMyYokimon.widthProperty().bind(b1);
            healthbarOppYokimon.widthProperty().bind(b2);

            root_draft.getChildren().add(healthbarMyYokimon);
            root_draft.getChildren().add(healthbarOppYokimon);
        }
    }
}
