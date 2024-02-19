package io.github.yokigroup.view;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.*;
import java.util.stream.Collectors;

public class CanvasPainter implements Painter {
    private final Map<String, Image> imageCache = new HashMap<>();
    private final GraphicsContext gc;
    private final List<SpriteData> drawQueue = new ArrayList<>();

    private Image consultCache(final String resourceURL) {
        if (!imageCache.containsKey(resourceURL)) {
            imageCache.put(resourceURL, new Image(resourceURL));
        }
        return imageCache.get(resourceURL);
    }

    private void sortDrawQueue() {
        drawQueue.sort(Comparator.comparingInt(SpriteData::priority));
    }

    public CanvasPainter(final GraphicsContext gc) {
        gc.setImageSmoothing(false);
        this.gc = gc;
    }

    private void paint(final SpriteData sprite) {
        final Canvas canvas = gc.getCanvas();
        final Vector2 canvasDim = new Vector2Impl(canvas.getWidth(), canvas.getHeight());
        final Vector2 absSpriteDim = sprite.getNormalizedDimension().times(canvasDim);
        final Vector2 absSpritePos = sprite.getNormalizedPosition().times(canvasDim).minus(absSpriteDim.scale(.5));

        gc.drawImage(
                consultCache(sprite.spriteURL()),
                absSpritePos.getX(),
                absSpritePos.getY(),
                absSpriteDim.getX(),
                absSpriteDim.getY()
        );
    }

    @Override
    public void addToPersistentDrawQueue(final SpriteData sprite) {
        if (sprite == null) {
            return;
        }
        addToPersistentDrawQueue(Set.of(sprite));
    }

    private Set<SpriteData> filterOutNullSpriteData(final Set<SpriteData> sprites) {
        return sprites.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Override
    public void addToPersistentDrawQueue(final Set<SpriteData> sprites) {
        synchronized (this) {
            drawQueue.addAll(filterOutNullSpriteData(sprites));
            sortDrawQueue();
        }
    }

    @Override
    public void removeFromPersistentDrawQueue(final SpriteData sprite) {
        if (sprite == null) {
            return;
        }
        removeFromPersistentDrawQueue(Set.of(sprite));
    }

    @Override
    public void removeFromPersistentDrawQueue(final Set<SpriteData> sprites) {
        synchronized (this) {
            drawQueue.removeAll(filterOutNullSpriteData(sprites));
            sortDrawQueue();
        }
    }

    @Override
    public void repaint() {
        synchronized (this) {
            drawQueue.forEach(this::paint);
        }
    }
}
