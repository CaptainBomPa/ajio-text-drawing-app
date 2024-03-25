package edu.mroz.interpreter.commands;

import edu.mroz.components.Canvas;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CommandsHolder {

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
        for (Command command : commandList) {
            if (command.matchRegex(stringCommand)) {
                command.execute(command.pullArgument(stringCommand), canvas);
            }
        }
    }

}
