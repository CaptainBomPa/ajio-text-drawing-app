package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.ConsoleLogAppender;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CommandsHolder {

    private static final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();
    public static final String SPLIT_COMMAND_REGEX = " (?=go|direction|color)";
    private static CommandsHolder instance;
    @Getter
    private final List<Command> commandList;

    private CommandsHolder() {
        commandList = new ArrayList<>();
        commandList.add(new GoCommand());
        commandList.add(new DirectionCommand());
        commandList.add(new ColorCommand());
    }

    public static synchronized CommandsHolder getInstance() {
        if (instance == null) {
            instance = new CommandsHolder();
        }
        return instance;
    }

    public void findAndRunCommand(String stringCommand, Canvas canvas) {
        boolean commandFound = false;
        for (Command command : commandList) {
            if (command.matchRegex(stringCommand)) {
                commandFound = true;
                command.execute(command.pullArgument(stringCommand), canvas);
                break;
            }
        }
        if (commandFound) {
            canvas.repaint();
        } else {
            consoleLogAppender.addErrorSystemLog("Command \"" + stringCommand + "\" not recognized.");
        }
    }

}
