package io.github.yokigroup.battleTest;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.AttackImpl;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import org.junit.jupiter.api.Test;

public class DMGCalcTest {

    /* public int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack) {
        return (int)attackedYokimon.getStat(Yokimon.Stats.ATK)*attack.attackPower()
                /attackedYokimon.getStat(Yokimon.Stats.DEF);
    } */

    @Test public void testBasicImpl() {

        Yokimon y1 = new YokimonImpl("Blue");
        Yokimon y2 = new YokimonImpl("Red");
        Attack a1 = new AttackImpl("Bubble", Attack.color.BLUE);
        Attack a2 = new AttackImpl("Leaf", Attack.color.GREEN);




    }
}

