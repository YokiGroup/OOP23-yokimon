package io.github.yokigroup.view.render;

import io.github.yokigroup.core.state.SpriteData;

import java.util.stream.Stream;

public interface DrawQueueReader {
    Stream<SpriteData> stream();
}
