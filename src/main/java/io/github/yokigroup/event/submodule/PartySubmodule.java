package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.PartySubmoduleAbs;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.view.observer.notification.NewYokimonNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link PartySubmoduleAbs}.
 * @author Giovanni Paone
 */
public class PartySubmodule extends PartySubmoduleAbs {
    private List<Yokimon> yokimonList;
    private final Publisher<NewYokimonNotification> newYokimonNotificationPub = new PublisherImpl<>();

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public PartySubmodule(final MessageHandler handler, ModelObserver modelObs) {
        super(handler);
        yokimonList = new ArrayList<>();
        modelObs.addNotificationPublisher(newYokimonNotificationPub);
    }

    private List<Yokimon> deepCopyOf(final List<Yokimon> list) {
        // TODO wait for a copy constructor of yokimon to be pushed upstream
        //return List.copyOf(list.stream().map(y -> new YokimonImpl(y)));
        return List.copyOf(list);
    }

    @Override
    public void addYokimon(final Yokimon y) {
        yokimonList.add(y);
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
    public boolean removeYokimon(final Yokimon y) {
        return yokimonList.remove(y);
    }

}
