package edu.mroz.components;

import lombok.Getter;

import java.awt.*;

@Getter
public class ColoredShape {

    private final Shape shape;
    private final Color color;
    private final boolean shouldDraw;

    public ColoredShape(Shape shape, Color color, boolean shouldDraw) {
        this.shape = shape;
        this.color = color;
        this.shouldDraw = shouldDraw;
    }
}
