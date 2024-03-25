package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.CanvasCurrentPointer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCommand implements Command {

    private static final Map<String, Color> colorMap = new HashMap<>();

    static {
        colorMap.put("RED", Color.RED);
        colorMap.put("BLUE", Color.BLUE);
        colorMap.put("GREEN", Color.GREEN);
        colorMap.put("BLACK", Color.BLACK);
    }

    private final CanvasCurrentPointer canvasCurrentPointer = CanvasCurrentPointer.getInstance();
    private final Pattern namedColorPattern = Pattern.compile("^color (RED|BLUE|GREEN|BLACK)$");
    private final Pattern hexColorPattern = Pattern.compile("^color (#[a-fA-F0-9]{6})$");

    @Override
    public boolean matchRegex(String value) {
        return namedColorPattern.matcher(value).matches() || hexColorPattern.matcher(value).matches();
    }

    @Override
    public String pullArgument(String value) {
        Matcher matcher = namedColorPattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        matcher = hexColorPattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Expected proper color command, but got invalid. Value=" + value);
    }

    @Override
    public void execute(String value, Canvas canvas) {
        Color color;
        if (colorMap.containsKey(value)) {
            color = colorMap.get(value);
        } else {
            try {
                color = Color.decode(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid hex color format: " + value);
                return;
            }
        }
        canvasCurrentPointer.setDrawingColor(color);
    }
}
