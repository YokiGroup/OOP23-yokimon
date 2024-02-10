package io.github.yokigroup.battleTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for Opponent AI.
 */
public class OpponentAITest {

    //FIXME -> fix yokimon constructor or get actual complete yokimons as test variables
    @BeforeEach
    public void init() {
        //TODO instantiate here your variables
    }

    /**
     * Testing dummy implementation.
     */
    @Test public void testDummyImpl() {
        /*
        OpponentAI toTest = new DummyImplOpponentAI(new BasicImplDmgCalculator());


        y1 = new YokimonImpl("Caterpie", Color.PURPLE, map1Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

        y2 = new YokimonImpl("Squirtle", Color.BLACK, map2Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);

        y3 = new YokimonImpl("Meowtwo", Color.WHITE, map1Stats, YokimonImpl.DEFAULT_GROWRATE,
                YokimonImpl.DEFAULT_LEVEL, map1Learnables);



        //empty
        assertEquals(Optional.empty(), toTest.getMove(y1));
        //one attack
        assertEquals(Optional.of(a1), toTest.getMove(y2));
        //multiple attacks
        assertEquals(Optional.of(a1), toTest.getMove(y3));

         */
    }
}
