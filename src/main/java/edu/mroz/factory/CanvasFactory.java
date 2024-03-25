package edu.mroz.factory;

import edu.mroz.components.Canvas;

public class CanvasFactory {

    public static Canvas createCanvas() {
        return new Canvas();
    }
}
