package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeCommand implements Command {

    private static final PointerParameters pointerParameters = PointerParameters.getInstance();
    private final Pattern pattern = Pattern.compile("^(size)\\s+(\\d+(?:\\.\\d)?)$");
    private final Pattern commandPattern = Pattern.compile("^(size)\\s+(\\S*)$");

    @Override
    public String getCommandName() {
        return "size";
    }

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).find();
    }

    @Override
    public Object pullArgument(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(2);
        }
        CommandErrorHandler.handleError(commandPattern, value);
        return null;
    }

    @Override
    public void execute(Object value, Canvas canvas) {
        final float size = Float.parseFloat((String) value);
        pointerParameters.setStrokeSize(size);
    }
}
