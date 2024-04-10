package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CircleCommand implements Command {

    private final Pattern commandPattern = Pattern.compile("^(circle)\\s*(\\S*)\\s*(\\S*)\\s*(\\S*)$");
    private final Pattern pattern = Pattern.compile("^(circle) (\\d+) (100|[1-9]?\\d) (true|false)$");

    @Override
    public String getCommandName() {
        return "circle";
    }

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).find();
    }

    @Override
    public Object pullArgument(String value) {
        Matcher matcher = commandPattern.matcher(value);
        int rValue;
        int percentValue;
        boolean clockwise;
        matcher.find();
        try {
            rValue = Integer.parseInt(matcher.group(2));
        } catch (IllegalStateException | IndexOutOfBoundsException | NumberFormatException e) {
            CommandErrorHandler.handleCircleCommandError(value, "Failed parsing first argument.");
            return null;
        }
        try {
            percentValue = Integer.parseInt(matcher.group(3));
        } catch (IllegalStateException | IndexOutOfBoundsException | NumberFormatException e) {
            CommandErrorHandler.handleCircleCommandError(value, "Failed parsing second argument.");
            return null;
        }
        try {
            clockwise = parseBooleanStrict(matcher.group(4));
        } catch (IllegalStateException | IndexOutOfBoundsException | IllegalArgumentException e) {
            CommandErrorHandler.handleCircleCommandError(value, "Failed parsing third argument.");
            return null;
        }
        return new CircleArgumentsWrapper(rValue, percentValue, clockwise);
    }

    @Override
    public void execute(Object value, Canvas canvas) {
        CircleArgumentsWrapper wrapper = (CircleArgumentsWrapper) value;
        canvas.drawCircle(wrapper.r, wrapper.percent, wrapper.clockwise);
    }

    private boolean parseBooleanStrict(String value) throws IllegalArgumentException {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        } else if ("false".equalsIgnoreCase(value)) {
            return false;
        } else {
            throw new IllegalArgumentException("Value is not a valid boolean: " + value);
        }
    }

    @Getter
    @RequiredArgsConstructor
    static class CircleArgumentsWrapper {
        private final int r;
        private final double percent;
        private final boolean clockwise;
    }

}
