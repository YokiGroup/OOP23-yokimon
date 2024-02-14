package io.github.yokigroup.battle;

import java.util.HashMap;
import java.util.Map;

/**
 * Database used for test classes
 */
public class YokimonDatabase {
    public static final Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.Effect.NONE);
    public static final Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.Effect.NONE);
    public static final Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.Effect.NONE);
    public static final Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.Effect.NONE);
    public static final Attack flameThrow = new AttackImpl("flame throw", Color.RED, 95, Attack.Effect.NONE);
    public static final Attack headBut = new AttackImpl("headBut", Color.WHITE, 50, Attack.Effect.NONE);
    public static final Attack tailSwipe = new AttackImpl("TailSwipe ", Color.WHITE, 60 , Attack.Effect.NONE);
    //future effect
    public static final Attack whirlpool = new AttackImpl("whirlpool ", Color.BLACK, 20 , Attack.Effect.NONE);
    public static final Attack bite = new AttackImpl("bite ", Color.BLACK, 60, Attack.Effect.NONE);
    //effect
    public static final Attack revenge = new AttackImpl("revenge ", Color.RED, 60 , Attack.Effect.NONE);
    public static final Attack confusion = new AttackImpl("confusion", Color.PURPLE, 50, Attack.Effect.NONE);
    //effect
    public static final Attack illusion = new AttackImpl("Illusion", Color.PURPLE, 0, Attack.Effect.NONE);
    //works only if one or more of the yokimon other team is dead (for nekomata)
    public static final Attack soulAttack = new AttackImpl("Soul Attack", Color.BLACK, 100, Attack.Effect.NONE);
    public static final Attack scratch = new AttackImpl("scratch", Color.WHITE, 40 , Attack.Effect.NONE);
    //regenerate half of the damage (for baku)
    public static final Attack dreamEater = new AttackImpl("dreamEater", Color.PURPLE, 60, Attack.Effect.NONE);
    //only for kitsune
    public static final Attack nineTailSmash = new AttackImpl("nineTailSmash", Color.WHITE, 140, Attack.Effect.NONE);
    //effect
    public static final Attack transformation = new AttackImpl("transformation", Color.PURPLE, 0 , Attack.Effect.NONE);

    public static final Map<Integer, Attack> stone= new HashMap<>();
    public static final Map<Yokimon.Stats, Integer> baseStats= new HashMap<>();

    private static void init() {

        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);

        baseStats.put(Yokimon.Stats.HP, 90);
        baseStats.put(Yokimon.Stats.ATK, 80);
        baseStats.put(Yokimon.Stats.DEF, 100);
        baseStats.put(Yokimon.Stats.SPD, 40);
    }

   public static Yokimon getOni() {
       init();
       return new YokimonImpl("oni", Color.RED, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

    public static Yokimon getNekomata() {
        init();
        return new YokimonImpl("nekomata", Color.PURPLE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

    public static Yokimon getBaku() {
        init();
       return new YokimonImpl("baku", Color.PURPLE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

   public static Yokimon getKitsune() {
        init();
       return new YokimonImpl("kitsune", Color.WHITE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

   public static Yokimon getTanuki() {
        init();
       return new YokimonImpl("tanuki", Color.WHITE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

}
