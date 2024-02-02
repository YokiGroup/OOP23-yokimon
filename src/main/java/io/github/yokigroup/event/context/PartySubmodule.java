package io.github.yokigroup.event.context;

import io.github.yokigroup.battle.Yokimon;

import java.util.ArrayList;
import java.util.List;

public class PartySubmodule implements Submodule{
    List<Yokimon> yokimonList = new ArrayList<>();

    void addYokimon(Yokimon y) {
        yokimonList.add(y);
    }

    List<Yokimon> listYokimons() {
        return yokimonList;
    }

    boolean removeYokimon(Yokimon y) {
        return yokimonList.remove(y);
    }

    @Override
    public void process() {

    }
}
