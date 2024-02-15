package io.github.yokigroup.event.observer;

/**
 * Publisher interface, responsible for adding and updating subscribers to a certain argument.
 * @param <T> the argument type (e.g. Fight).
 */
public interface Publisher<T> {

    /**
     * Add an observer to subscribe to this publisher.
     * @param obs the observer that must be added.
     */
    void addObserver(EObserver<T> obs);

    /**
     * Notify observers about some changes.
     * @param arg the element that the observer is observing and that has just changed.
     */
    void notifyObservers(T arg);
}
