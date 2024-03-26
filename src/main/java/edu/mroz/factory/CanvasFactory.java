package edu.mroz.factory;

import edu.mroz.components.Canvas;

public class CanvasFactory {

    private CanvasFactory() {
    }

    public static Canvas createCanvas() {
        return new Canvas();
    }
}
