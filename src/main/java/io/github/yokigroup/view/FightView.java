package io.github.yokigroup.view;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.fight.Fight;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FightView {

    public void drawHPbars(final Fight fight) {

        Rectangle healthbarMyYokimon = new Rectangle(200.0, 50.0, Color.GREY);
        Rectangle healthbarOppYokimon = new Rectangle(200.0, 50.0, Color.GREY);

        DoubleProperty healthPercentage1 = new SimpleDoubleProperty(fight.getHPPercentage(fight.getCurrentMyYokimon()));
        DoubleProperty healthPercentage2 = new SimpleDoubleProperty(fight.getHPPercentage(fight.getCurrentOpponent()));

        DoubleBinding b1 = healthbarMyYokimon.widthProperty().multiply(healthPercentage1);
        DoubleBinding b2 = healthbarOppYokimon.widthProperty().multiply(healthPercentage2);
        healthbarMyYokimon.widthProperty().bind(b1);
        healthbarOppYokimon.widthProperty().bind(b2);

        //mainPane.getChildren().add(healthbarMyYokimon);
        //mainPane.getChildren().add(healthbarOppYokimon);
    }
}
