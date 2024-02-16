package io.github.yokigroup.battle;

import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.file.loader.AttackLoader;
import io.github.yokigroup.file.loader.YokimonLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.*;
import java.util.HashMap;
import java.util.Map;

class YokimonImplTest {

    private static YokimonLoader loader;
    private static AttackLoader loaderAttack;
    private static MessageHandler messageHandler;
    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
        loaderAttack = new AttackLoader();
        MessageHandler messageHandler = new GameMessageHandler();
    }

    @Test
    void YokimonImpl(){
        YokimonImpl stone = null;
        assertThrows(NullPointerException.class, () -> new YokimonImpl(stone));

    }
    @Test
    void getAllStats() {
    Yokimon oni = loader.load(1);

    oni.setLevel(5);

    assertEquals(5, oni.getLevel());

    }

    @Test
    void getAttacks() {
    }

    @Test
    void setExpNext() {
/*
        Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.Effect.NONE);
        Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.Effect.NONE);
        Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.Effect.NONE);
        Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.Effect.NONE);
*/

        /*
        Attack  = new AttackImpl("", Color.WHITE, , Attack.effect.NONE);
        */
        Map<Integer, Attack> stone= new HashMap<>();
/*
        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);
        Map<Yokimon.Stats, Integer> baseStats1= new HashMap<>();
        baseStats1.put(Yokimon.Stats.HP, 90);
        baseStats1.put(Yokimon.Stats.ATK, 80);
        baseStats1.put(Yokimon.Stats.DEF, 100);
        baseStats1.put(Yokimon.Stats.SPD, 40);
        Map<Yokimon.Stats, Integer> baseStats2= new HashMap<>();
        baseStats2.put(Yokimon.Stats.HP, 120);
        baseStats2.put(Yokimon.Stats.ATK, 100);
        baseStats2.put(Yokimon.Stats.DEF, 60);
        baseStats2.put(Yokimon.Stats.SPD, 70);
        BasicImplDmgCalculator calc = new BasicImplDmgCalculator();
        Yokimon oni = new YokimonImpl("oni", Color.RED, baseStats1, Yokimon.GrowthRate.MEDIUM, 10, stone );
        Yokimon wani = new YokimonImpl("wani", Color.RED, baseStats2, Yokimon.GrowthRate.SLOW, 10, stone );
        assertEquals(42, oni.getStat(Yokimon.Stats.HP));
        assertEquals(12, calc.getDMG(oni, wani, slap));
*/
    }
}