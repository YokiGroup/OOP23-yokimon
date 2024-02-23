package io.github.yokigroup.view.render.drawqueue;

import io.github.yokigroup.view.render.drawable.SpriteData;

import java.util.stream.Stream;

public interface DrawQueueReader {
    Stream<SpriteData> stream();
}
