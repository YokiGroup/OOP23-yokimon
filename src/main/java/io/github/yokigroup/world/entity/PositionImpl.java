package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.MutablePair;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
public class PositionImpl implements Position{

    private static double EXPONENT = 2.00;
    private MutablePair pos;

    //private final MessageHandler messageHandler;

    /**
     * Constructor of the PositionImpl class, with the specified values
     * @param pos pos = Pair<>
     * //@param message messageHandler
     */
    public PositionImpl(MutablePair pos) {
        this.pos = pos;
        //this.messageHandler = message;
    }

    @Override
    public MutablePair getPosition() {
        return this.pos;
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
    public boolean inRadius(Position otherPos, double radius) {
        double dist = getDistance((double)this.pos.getX(), (double)otherPos.getPosition().getX(),
                (double)this.pos.getY(), (double)otherPos.getPosition().getY());
        return dist <= radius;

    }


    private Float getDistance(double x1, double x2, double y1, double y2){
        double pow1 = pow(x2 - x1, EXPONENT);
        double pow2 = pow(y2 - y1, EXPONENT);
        return (float)sqrt(pow1+pow2);
    }
}
