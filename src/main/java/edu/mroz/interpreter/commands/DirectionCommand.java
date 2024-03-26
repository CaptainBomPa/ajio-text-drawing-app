package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectionCommand implements Command {

    private static final PointerParameters POINTER_PARAMETERS = PointerParameters.getInstance();

    private final Pattern pattern = Pattern.compile("^(direction) ([+-]?\\d{1,3}|360)$");
    private final Pattern commandPattern = Pattern.compile("^(direction)\\s*(\\S*)$");

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).matches();
    }

    @Override
    public String pullArgument(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(2);
        }
        CommandErrorHandler.handleError(commandPattern, value);
        return null;
    }

    @Override
    public void execute(String value, Canvas canvas) {
        int currentDirection = POINTER_PARAMETERS.getDirection();
        int change;
        int newDirection;

        if (value.startsWith("+") || value.startsWith("-")) {
            change = Integer.parseInt(value);
            newDirection = currentDirection + change;
        } else {
            newDirection = Integer.parseInt(value);
        }

        newDirection %= 360;
        if (newDirection < 0) {
            newDirection += 360;
        }
        POINTER_PARAMETERS.setDirection(newDirection);
    }
}
