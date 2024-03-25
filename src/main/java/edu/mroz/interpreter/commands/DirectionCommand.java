package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.CanvasCurrentPointer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectionCommand implements Command {

    private static final CanvasCurrentPointer canvasCurrentPointer = CanvasCurrentPointer.getInstance();

    private final Pattern pattern = Pattern.compile("^(direction) (\\d{1,3}|360)$");

    @Override
    public boolean matchRegex(String value) {
        return pattern.matcher(value).find();
    }

    @Override
    public String pullArgument(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(2);
        }
        throw new IllegalArgumentException("Expected proper command, but got invalid. Value=" + value);
    }

    @Override
    public void execute(String value, Canvas canvas) {
        canvasCurrentPointer.setDirection(Integer.parseInt(value));
    }
}
