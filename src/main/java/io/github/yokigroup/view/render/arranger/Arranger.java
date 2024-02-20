package io.github.yokigroup.view.render.arranger;

import io.github.yokigroup.view.render.observer.ModelObserver;

public abstract class Arranger {
    private final ModelObserver modelObs;

    public Arranger(final ModelObserver modelObs) {
        this.modelObs = modelObs;
    }
}
