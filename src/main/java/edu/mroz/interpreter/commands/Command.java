package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;

public interface Command {

    boolean matchRegex(String value);

    String pullArgument(String value);

    void execute(String value, Canvas canvas);
}
