package io.github.yokigroup.battle;

import java.util.List;
import java.util.Map;

public class YokimonBuilder{

    //POSSO USARE MAP.COPYOF E LIST.COPYOF NEL BUILDER O ESPLODE TUTTO SE POI VOGLIO MODIFICARE QUESTI CAMPI?
    //POSSO TOGLIERE IL FINAL DAI CAMPI FINAL NEL BUILDER?
    private String name; //final
    private Attack.color color; //final
    private Map<Yokimon.Stats, Integer> BaseStats; //final
    private Map<Yokimon.Stats, Integer> Stats;
    private int level;
    private Yokimon.GrowRate growRate; //final
    private List<Attack> moves;
    private boolean active;

    /**
     * final fields of YokimonImpl. Their initialization is mandatory.
     */
    public YokimonBuilder(String name, Attack.color color,
                   Map<Yokimon.Stats, Integer> BaseStats, Yokimon.GrowRate growRate){
        this.name = name;
        this.color = color;
        this.BaseStats = Map.copyOf(BaseStats);
        this.growRate = growRate;
    }

    public YokimonBuilder stats(Map<Yokimon.Stats, Integer> Stats) {
        this.Stats = Map.copyOf(Stats);
        return this;
    }

    public YokimonBuilder level(int level) {
        this.level = level;
        return this;
    }

    public YokimonBuilder moves(List<Attack> moves) {
        this.moves = List.copyOf(moves);
        return this;
    }

    public YokimonBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public YokimonImpl build() throws IllegalStateException {
        if (this.name == null || this.color == null || this.BaseStats == null || this.growRate == null) {
            throw new IllegalStateException("");
        }
        return new YokimonImpl(this.name, this.color, this.BaseStats, this.Stats,
                this.level, this.growRate, this.moves, this.active);
    }
}
