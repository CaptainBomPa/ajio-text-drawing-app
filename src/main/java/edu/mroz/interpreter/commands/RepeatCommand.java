package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepeatCommand implements Command {

    public static final String REPEAT_COMMAND = "repeat";
    private final Pattern commandPattern = Pattern.compile("^(repeat)(?:\\s+(\\S+))?$");
    private final Pattern pattern = Pattern.compile("^(repeat)\\s+(\\d+)$");

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
        CommandErrorHandler.handleError(commandPattern, value);
        return null;
    }

    @Override
    public void execute(Object value, Canvas canvas) {
        //not needed
    }
}
