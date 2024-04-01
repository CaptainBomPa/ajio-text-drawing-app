package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollbackCommand implements Command {

    private static final PointerParameters pointerParameters = PointerParameters.getInstance();
    private final Pattern pattern = Pattern.compile("^(rollback) (\\d+)$");
    private final Pattern commandPattern = Pattern.compile("^(rollback)\\s*(\\S*)$");

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).find();
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
        int rollbackTimes = Integer.parseInt(((String) value));

        for (int i = 0; i < rollbackTimes; i++) {
            canvas.removeLastShape();
        }
        canvas.repaint();
    }
}
