package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoCommand implements Command {

    private static final PointerParameters pointerParameters = PointerParameters.getInstance();
    private final Pattern pattern = Pattern.compile("^(go) (\\d+)$");
    private final Pattern commandPattern = Pattern.compile("^(go)\\s*([^\\s]*)$");

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
    public void execute(String value, Canvas canvas) {
        int distance = Integer.parseInt(value);
        double directionRadians = Math.toRadians(pointerParameters.getDirection());

        int deltaX = (int) (distance * Math.sin(directionRadians));
        int deltaY = (int) (distance * Math.cos(directionRadians));

        int endX = pointerParameters.getCurrentPointPosition().x + deltaX;
        int endY = pointerParameters.getCurrentPointPosition().y - deltaY;

        canvas.drawLine(endX, endY);
    }
}
