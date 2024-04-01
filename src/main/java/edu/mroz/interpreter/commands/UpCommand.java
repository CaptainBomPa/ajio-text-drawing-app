package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.data.PointerState;

import java.util.regex.Pattern;

public class UpCommand implements Command {

    private static final PointerParameters pointerParameters = PointerParameters.getInstance();
    private final Pattern commandPattern = Pattern.compile("^(up)");

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).find();
    }

    @Override
    public String pullArgument(String value) {
        //not needed for simple command
        return null;
    }

    @Override
    public void execute(Object value, Canvas canvas) {
        pointerParameters.setPointerState(PointerState.UP);
    }
}
