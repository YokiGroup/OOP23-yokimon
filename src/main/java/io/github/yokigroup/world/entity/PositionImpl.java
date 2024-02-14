package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.*;

import java.util.Objects;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
public class PositionImpl implements Position{

    private final static double EXPONENT = 2.00;
    private Vector2 pos;

    //private final MessageHandler messageHandler;

    /**
     * Constructor of the PositionImpl class, with the specified values
     * @param pos pos = Pair<>
     * //@param message messageHandler
     */
    public PositionImpl(Vector2 pos) {
        Objects.requireNonNull(pos, "Pos passed in PositionImpl was null");
        this.pos = new Vector2Impl(pos);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2Impl(this.pos);
    }

    @Override
    public void setPosition(Vector2 pos) {
        this.pos = new Vector2Impl(pos);
    }

    @Override
    public void movePosition(Vector2 vector) {
        this.pos = this.pos.plus(vector);
    }

    @Override
    public Position testTovePosition(Vector2 vector) {
        return new PositionImpl(this.pos.plus(vector));
    }

    @Override
    public boolean isValid(MessageHandler messageHandler) {
        Objects.requireNonNull(messageHandler, "Message was null");
        messageHandler.handle(GameMapSubmodule.class, map -> {
        });
        return true;
    }

    @Override
    public boolean inRadius(Position otherPos, double radius) {
        Objects.requireNonNull(otherPos, "Pos passed in inRadius method was null");
        double dist = getDistance(this.pos.getX(), otherPos.getPosition().getX(),
                this.pos.getY(), otherPos.getPosition().getY());
        return dist <= radius;

    }


    private Float getDistance(double x1, double x2, double y1, double y2){
        double pow1 = pow(x2 - x1, EXPONENT);
        double pow2 = pow(y2 - y1, EXPONENT);
        return (float)sqrt(pow1+pow2);
    }
}
