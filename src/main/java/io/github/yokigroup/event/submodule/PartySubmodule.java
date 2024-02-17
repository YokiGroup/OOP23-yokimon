package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.PartySubmoduleAbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Submodule containing a yokimon's party information.
 * @author Giovanni Paone
 */
public final class PartySubmodule extends PartySubmoduleAbs {
    private List<Yokimon> yokimonList;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public PartySubmodule(final MessageHandler handler) {
        super(handler);
        yokimonList = new ArrayList<>();
    }

    private List<Yokimon> deepCopyOf(final List<Yokimon> list) {
        return list.stream().map(YokimonImpl::new).map(Yokimon.class::cast).toList();
    }

    @Override
    public void addYokimon(final Yokimon y) {
        yokimonList.add(new YokimonImpl(y));
    }

    @Override
    public List<Yokimon> listYokimons() {
        return deepCopyOf(yokimonList);
    }

    @Override
    public void setParty(final List<Yokimon> party) {
        yokimonList = deepCopyOf(party);
    }

    @Override
    public void removeYokimon(final int index) {
        yokimonList.remove(index);
    }

}
