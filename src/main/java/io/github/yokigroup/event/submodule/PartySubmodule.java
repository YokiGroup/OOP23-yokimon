package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;

import java.util.ArrayList;
import java.util.List;

public class PartySubmodule implements Submodule{
    List<Yokimon> yokimonList = new ArrayList<>();

    public void addYokimon(Yokimon y) {
        yokimonList.add(y);
    }

    public List<Yokimon> listYokimons() {
        return yokimonList;
    }

    public boolean removeYokimon(Yokimon y) {
        return yokimonList.remove(y);
    }

    @Override
    public void process() {

    }
}
