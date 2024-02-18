package io.github.yokigroup.event.observer;

/**
 * A class must implement this interface in order to subscribe to an observable.
 * @param <T> the type of the object that the observer wants to be notified about (e.g. Fight).
 */
public interface EObserver<T> {

    /**
     * Update the observer about some changes.
     * @param publisher the publisher responsible about notifying this observer.
     * @param lastArg last value possessed by arg on the last update call (starts out as {@code null})
     * @param arg the element that underwent some changes.
     */
    void update(PublisherImpl<T> publisher, T lastArg, T arg);
}
