package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.fight.FightImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.yokigroup.battle.*;

import java.util.LinkedList;
import java.util.List;

public class FightTest {

    private static Fight toTest;
    private static Yokimon y1, y2, y3, y4;
    private static List<Yokimon>  myParty, oppParty;

    @BeforeAll
    public static void init() {
        y1 = YokimonDatabase.getOni();
        y2 = YokimonDatabase.getBaku();
        y3 = YokimonDatabase.getKitsune();
        y4 = YokimonDatabase.getNekomata();

        myParty = new LinkedList<>();
        myParty.add(y1);
        myParty.add(y2);

        oppParty = new LinkedList<>();
        oppParty.add(y3);
        oppParty.add(y4);

        toTest = new FightImpl(myParty, oppParty);
    }

}


