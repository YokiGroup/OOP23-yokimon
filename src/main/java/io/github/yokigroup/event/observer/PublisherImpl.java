package io.github.yokigroup.event.observer;

import java.util.Set;
import java.util.HashSet;

/**
 * A publisher, responsible for adding and updating subscribers to a certain argument.
 * @param <T> the argument type (e.g. Fight).
 */
public final class PublisherImpl<T> implements Publisher<T> {

    /* The observers subscribed to this publisher */
    private final Set<EObserver<T>> observerSet = new HashSet<>();
    private T lastArg = null;

    @Override
    public void addObserver(final EObserver<T> obs) {
        observerSet.add(obs);
    }

    @Override
    public void notifyObservers(final T arg) {
        for (var obs : observerSet) {
            obs.update(this, lastArg, arg);
            lastArg = arg;

        }
    }
}
