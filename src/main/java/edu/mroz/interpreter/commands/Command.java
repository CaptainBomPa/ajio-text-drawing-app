package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;

public interface Command {

    String getCommandName();

    boolean matchRegex(String value);

    Object pullArgument(String value);

    void execute(Object value, Canvas canvas);
}
