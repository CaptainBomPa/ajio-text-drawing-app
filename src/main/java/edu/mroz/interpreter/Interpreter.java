package edu.mroz.interpreter;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.commands.CommandsHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Interpreter {

    private static final CommandsHolder commandsHolder = CommandsHolder.getInstance();
    private static Interpreter instance;

    public static synchronized Interpreter getInstance() {
        if (instance == null) {
            instance = new Interpreter();
        }
        return instance;
    }

    public void interpretCommand(String multipleCommands, Canvas canvas) {
        List<String> commands = new ArrayList<>(Arrays.asList(multipleCommands.split(CommandsHolder.SPLIT_COMMAND_REGEX)));
        for (String stringCommand : commands) {
            commandsHolder.findAndRunCommand(stringCommand, canvas);
        }
    }

}
