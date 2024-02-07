package io.github.yokigroup.battleTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DMGCalcTest {

    @Test public void testDummyImpl() {

        Yokimon y1 = new YokimonImpl("Blue");
        Yokimon y2 = new YokimonImpl("Red");
        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN);


    }
}

