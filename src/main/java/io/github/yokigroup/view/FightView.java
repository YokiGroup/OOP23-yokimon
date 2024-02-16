package io.github.yokigroup.view;

import io.github.yokigroup.battle.fight.Fight;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public final class FightView extends Application {

    private static final int SCREEN_WIDTH = 850;
    private static final int SCREEN_HEIGTH = 500;
    private static final int HPBAR_LENGTH = 250;
    private static final int HPBAR_HEIGTH = 35;
    private static final double DELTA = 20;
    private static final int ARC_DIMENSION = 15;
    private static final String DEFAULT_NAME1 = "YOKIMON 1";
    private static final String DEFAULT_NAME2 = "YOKIMON 2";
    private static final String DEFAULT_LEVEL = "LEVEL";
    private static final double YOKIMON_DIM = 200;
    private static Pane root_draft = new Pane();
    private Fight fight;
    private static final String POKEMON1_URL = "https://i.pinimg.com/originals/ef/33/2f/ef332f79619b7503ff40c766eb370219.png";
    private static final String POKEMON2_URL = "https://purepng.com/public/uploads/large/purepng.com-pokemonpokemonpocket-monsterspokemon-franchisefictional-speciesone-pokemonmany-pokemonone-pikachu-1701527784884jkxg4.png";
    private static final String BACKGROUND_URL = "https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/204364595/original/86db6005cd51b4f60e71cca277f603a82cf5646a/draw-a-pixel-pokemon-battle-background.png";
    private static final String BACKGROUND_PNG = "C:/Users/Marilia/Downloads/forest-background-5th-style-pass-portfolio-edit.png";

    /*
    public FightView(Fight fight) {
        this.fight = fight;
    }
     */

    @Override
    public void start(final Stage primaryStage) throws Exception {

        Scene scene = new Scene(root_draft, SCREEN_WIDTH, SCREEN_HEIGTH);

        setBackground();
        setHealthBars();
        setInfo();
        setYokimons();

        //FINAL SETUPS
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fight");
        primaryStage.show();
    }

    //TODO SUBSTITUTE WITH ACTUAL YOKIMONS
    private void setYokimons() {

        Image image1 = new Image("C:\\Users\\Marilia\\Downloads\\YOKIMON\\TANUKI.png");
        Image image2 = new Image("C:\\Users\\Marilia\\Downloads\\YOKIMON\\NEKOMATA.png");;

        //final Image image1 = new Image(POKEMON1_URL, YOKIMON_DIM, YOKIMON_DIM, false, true);
        //final Image image2 = new Image(POKEMON2_URL, YOKIMON_DIM, YOKIMON_DIM, false, true);
        ImagePattern pattern1 = new ImagePattern(image1);
        ImagePattern pattern2 = new ImagePattern(image2);

        Rectangle rect1 = new Rectangle(YOKIMON_DIM, YOKIMON_DIM);
        rect1.setX(DELTA);
        rect1.setY(SCREEN_HEIGTH - YOKIMON_DIM - 3 * DELTA);

        Rectangle rect2 = new Rectangle(YOKIMON_DIM, YOKIMON_DIM);
        rect2.setX(SCREEN_WIDTH - YOKIMON_DIM - DELTA);
        rect2.setY(SCREEN_HEIGTH - YOKIMON_DIM - 3 * DELTA);

        rect1.setFill(pattern1);
        rect2.setFill(pattern2);
        root_draft.getChildren().add(rect1);
        root_draft.getChildren().add(rect2);
    }


    // TODO MUST REPLACE URL WITH PNG
    private void setBackground() {
        BackgroundImage backgroundImage= new BackgroundImage(new Image(BACKGROUND_URL,SCREEN_WIDTH,SCREEN_HEIGTH,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root_draft.setBackground(new Background(backgroundImage));
    }

    private void setHealthBars() {
        /*
        final Rectangle healthbar1 = new Rectangle(getLengthHPBar(fight.getHPPercentage(fight.getCurrentMyYokimon())),
                HPBAR_HEIGTH,
                getColorHPBar(fight.getHPPercentage(fight.getCurrentOpponent())));
        healthbar1.setX(DELTA);
        healthbar1.setY(DELTA);
        healthbar1.setArcHeight(ARC_DIMENSION);
        healthbar1.setArcWidth(ARC_DIMENSION);
        healthbar1.setStyle("-fx-stroke: grey; -fx-stroke-width: 4;");

        final Rectangle healthbar2 = new Rectangle(getLengthHPBar(fight.getHPPercentage(fight.getCurrentOpponent())),
                HPBAR_HEIGTH,
                getColorHPBar(fight.getHPPercentage(fight.getCurrentOpponent())));
        healthbar2.setX(SCREEN_WIDTH - getLengthHPBar(fight.getHPPercentage(fight.getCurrentOpponent())) - DELTA);
        healthbar2.setY(DELTA);
        healthbar2.setArcHeight(ARC_DIMENSION);
        healthbar2.setArcWidth(ARC_DIMENSION);
        healthbar2.setStyle("-fx-stroke: grey; -fx-stroke-width: 4;");
         */

        final Rectangle healthbar1_dbg = new Rectangle(HPBAR_LENGTH, HPBAR_HEIGTH, Color.GREEN);
        healthbar1_dbg.setX(DELTA);
        healthbar1_dbg.setY(DELTA);
        healthbar1_dbg.setArcHeight(ARC_DIMENSION);
        healthbar1_dbg.setArcWidth(ARC_DIMENSION);
        healthbar1_dbg.setStyle("-fx-stroke: grey; -fx-stroke-width: 4;");

        final Rectangle healthbar2_dbg = new Rectangle(HPBAR_LENGTH, HPBAR_HEIGTH, Color.GREEN);
        healthbar2_dbg.setX(SCREEN_WIDTH - HPBAR_LENGTH - DELTA);
        healthbar2_dbg.setY(DELTA);
        healthbar2_dbg.setArcHeight(ARC_DIMENSION);
        healthbar2_dbg.setArcWidth(ARC_DIMENSION);
        healthbar2_dbg.setStyle("-fx-stroke: grey; -fx-stroke-width: 4;");

        //TODO SUBSTITUTE ACTUAL HEALTH BARS
        root_draft.getChildren().add(healthbar1_dbg);
        root_draft.getChildren().add(healthbar2_dbg);
    }

    public void drawHPbars(final Fight fight) {

        Rectangle healthbarMyYokimon = new Rectangle(200.0, 50.0, Color.GREY);
        Rectangle healthbarOppYokimon = new Rectangle(200.0, 50.0, Color.GREY);

        //TODO USE THIS IN NEW METHOD
        DoubleProperty healthPercentage1 = new SimpleDoubleProperty(fight.getHPPercentage(fight.getCurrentMyYokimon()));
        DoubleProperty healthPercentage2 = new SimpleDoubleProperty(fight.getHPPercentage(fight.getCurrentOpponent()));

        DoubleBinding b1 = healthbarMyYokimon.widthProperty().multiply(healthPercentage1);
        DoubleBinding b2 = healthbarOppYokimon.widthProperty().multiply(healthPercentage2);
        healthbarMyYokimon.widthProperty().bind(b1);
        healthbarOppYokimon.widthProperty().bind(b2);
        //TODO END

        root_draft.getChildren().add(healthbarMyYokimon);
        root_draft.getChildren().add(healthbarOppYokimon);
    }

    //FIXME
    //loadfont
    //classpath- > input stream dal font della classpath
    private void setInfo() {

        javafx.scene.text.Font.getFamilies();

        //final Label Name1 = new Label(fight.getCurrentMyYokimon().getName().toUpperCase());
        final Label Name1 = new Label(DEFAULT_NAME1);
        Name1.setLayoutX(DELTA);
        Name1.setLayoutY(DELTA + HPBAR_HEIGTH + (double)DELTA/3);
        Name1.setStyle("-fx-text-fill: black ; -fx-font-size: 24;");

        //final Label Name2 = new Label(fight.getCurrentOpponent().getName().toUpperCase());
        final Label Name2 = new Label(DEFAULT_NAME2);
        Name2.setLayoutX(SCREEN_WIDTH - (17)*DEFAULT_NAME2.length());
        Name2.setLayoutY(DELTA + HPBAR_HEIGTH + DELTA/3);
        Name2.setStyle("-fx-text-fill: black ; -fx-font-size: 24;");

        //final Label Level1 = new Label(DEFAULT_LEVEL + fight.getCurrentMyYokimon().getLevel());
        final Label Level1 = new Label(DEFAULT_LEVEL + "14");
        Level1.setLayoutX(DELTA);
        Level1.setLayoutY(DELTA + HPBAR_HEIGTH + DELTA*1.5);
        Level1.setStyle("-fx-text-fill: black ; -fx-font-size: 24;");

        //final Label Level2 = new Label(DEFAULT_LEVEL + fight.getCurrentOpponent().getLevel());
        final Label Level2 = new Label(DEFAULT_LEVEL + "15");
        Level2.setLayoutX(SCREEN_WIDTH - (12)*DEFAULT_NAME2.length());
        Level2.setLayoutY(DELTA + HPBAR_HEIGTH + (double)DELTA*1.5);
        Level2.setStyle("-fx-text-fill: black ; -fx-font-size: 24;");

        root_draft.getChildren().add(Name1);
        root_draft.getChildren().add(Name2);
        root_draft.getChildren().add(Level1);
        root_draft.getChildren().add(Level2);
    }

    private Color getColorHPBar(int percentage) {
        if (percentage >= 80) {
            return Color.DARKGREEN;
        }
        else if (percentage>= 40) {
            return Color.YELLOWGREEN;
        }
        return Color.DARKRED;
    }

    private int getLengthHPBar(int percentage) {
        return (int)((double)percentage * HPBAR_LENGTH / 100) ;
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
    }
}
