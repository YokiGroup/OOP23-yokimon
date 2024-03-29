package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.yokimon.YokimonImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.PartySubmoduleAbs;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.view.notification.NewYokimonNotificationImpl;
import io.github.yokigroup.view.notification.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PartySubmoduleAbs}.
 * @author Giovanni Paone
 */
public final class PartySubmodule extends PartySubmoduleAbs {
    private List<Yokimon> yokimonList;
    private final Publisher<Notification> newYokimonNotificationPub = new PublisherImpl<>();

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     * @param modelObs the model Observer.
     */
    public PartySubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        yokimonList = new ArrayList<>();
        modelObs.addNotificationPublisher(newYokimonNotificationPub);
    }

    private List<Yokimon> deepCopyOf(final List<Yokimon> list) {
        Objects.requireNonNull(list);
        return list.stream().map(YokimonImpl::new).collect(Collectors.toList());
    }

    @Override
    public void addYokimon(final Yokimon y) {
        yokimonList.add(new YokimonImpl(y));
        newYokimonNotificationPub.notifyObservers(new NewYokimonNotificationImpl(y.getName()));
    }

    @Override
    public List<Yokimon> listYokimons() {
        return deepCopyOf(yokimonList);
    }

    @Override
    public void setParty(final List<Yokimon> party) {
        yokimonList = deepCopyOf(party);
    }
}
