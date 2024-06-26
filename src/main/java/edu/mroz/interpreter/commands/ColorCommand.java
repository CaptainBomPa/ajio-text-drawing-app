package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.data.PointerParameters;
import edu.mroz.interpreter.commands.utils.CommandErrorHandler;
import edu.mroz.utils.ConsoleLogAppender;

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
        colorMap.put("GRAY", Color.GRAY);
        colorMap.put("PINK", Color.PINK);
        colorMap.put("WHITE", Color.WHITE);
        colorMap.put("YELLOW", Color.YELLOW);
    }

    private final PointerParameters pointerParameters = PointerParameters.getInstance();
    private final Pattern commandPattern = Pattern.compile("^(color)\\s*(\\S*)$");
    private final Pattern namedColorPattern = Pattern.compile("^color (RED|BLUE|GREEN|BLACK|GRAY|PINK|WHITE|YELLOW)$", Pattern.CASE_INSENSITIVE);
    private final Pattern hexColorPattern = Pattern.compile("^color (#[a-f0-9]{6})$", Pattern.CASE_INSENSITIVE);
    private final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    @Override
    public String getCommandName() {
        return "color";
    }

    @Override
    public boolean matchRegex(String value) {
        return commandPattern.matcher(value).matches();
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
        CommandErrorHandler.handleError(commandPattern, value);
        return null;
    }

    @Override
    public void execute(Object value, Canvas canvas) {
        Color color;
        if (colorMap.containsKey(((String) value).toUpperCase())) {
            color = colorMap.get(((String) value).toUpperCase());
        } else {
            try {
                color = Color.decode(((String) value));
            } catch (NumberFormatException e) {
                String errorLogString = "Invalid hex color format: " + value;
                consoleLogAppender.addErrorSystemLog(errorLogString);
                return;
            }
        }
        pointerParameters.setDrawingColor(color);
    }
}
