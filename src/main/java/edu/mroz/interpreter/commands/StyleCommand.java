package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.DrawingStyle;
import edu.mroz.data.PointerParameters;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyleCommand implements Command {

    private final PointerParameters pointerParameters = PointerParameters.getInstance();
    private final Pattern commandPattern = Pattern.compile("^(style)\\s*(\\S*)$");
    private final Pattern pattern = Pattern.compile("^style (line|dashed|dotted)$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).find();
    }

    @Override
    public Object pullArgument(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        CommandErrorHandler.handleError(commandPattern, value);
        return null;
    }

    @Override
    public void execute(Object value, Canvas canvas) {
        DrawingStyle drawingStyle = DrawingStyle.valueOf(((String) value).toUpperCase());
        pointerParameters.setDrawingStyle(drawingStyle);
    }
}
