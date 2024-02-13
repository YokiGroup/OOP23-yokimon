package io.github.yokigroup.core;

import java.util.Set;
import java.util.HashSet;

public class PublisherImpl<T> implements Publisher<T> {

    /* The observers subscribed to this publisher */
    private final Set<EObserver<T>> observerSet = new HashSet<>();

    @Override
    public void addObserver(EObserver<T> obs) {
        observerSet.add(obs);
    }

    @Override
    public void notifyObservers (T arg) {
        for (var obs : observerSet) {
            obs.update(this, arg);
        }
    }
}
