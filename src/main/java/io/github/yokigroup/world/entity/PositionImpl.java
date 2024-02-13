package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.MutablePair;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;

import java.util.Objects;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
public class PositionImpl implements Position{

    private final static double EXPONENT = 2.00;
    private MutablePair pos;

    //private final MessageHandler messageHandler;

    /**
     * Constructor of the PositionImpl class, with the specified values
     * @param pos pos = Pair<>
     * //@param message messageHandler
     */
    public PositionImpl(MutablePair pos) {
        Objects.requireNonNull(pos, "Pos passed in PositionImpl was null");
        this.pos = pos.copyOf();
        //this.messageHandler = message;
    }

    @Override
    public MutablePair getPosition() {
        return this.pos.copyOf();
    }

    @Override
    public void setPosition(MutablePair newPos) {
        //TODO control if the position is valid
        this.pos.setPair(newPos);
    }

    @Override
    public void movePosition(Vector2 vector) {
        this.pos.addVector(vector);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Vector2 turnIntoVector() {
        return new Vector2Impl(this.getPosition().getX(), this.getPosition().getY());
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
