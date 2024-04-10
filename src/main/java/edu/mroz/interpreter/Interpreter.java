package edu.mroz.interpreter;

import edu.mroz.components.Canvas;
import edu.mroz.data.CommandsHolder;
import edu.mroz.interpreter.commands.RepeatCommand;
import edu.mroz.utils.ConsoleLogAppender;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Interpreter {

    private static final CommandsHolder commandsHolder = CommandsHolder.getInstance();
    private static final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();
    private static Interpreter instance;

    public static synchronized Interpreter getInstance() {
        if (instance == null) {
            instance = new Interpreter();
        }
        return instance;
    }

    public void interpretCommand(String multipleCommands, Canvas canvas) {
        List<String> commands = new ArrayList<>(Arrays.asList(multipleCommands.trim().split(commandsHolder.getCommandsRegexToSplit())));

        if (containsRepeatCommand(commands)) {
            if (startsWithRepeatCommand(commands)) {
                RepeatCommand repeatCommand = new RepeatCommand();
                int numberOfLoops = Integer.parseInt(repeatCommand.pullArgument(commands.get(0)));
                commands.remove(0);
                for (int i = 0; i < numberOfLoops; i++) {
                    searchAndRunCommand(commands, canvas);
                }
            } else {
                consoleLogAppender.addErrorSystemLog("Repeat command must be first.");
            }
        } else {
            searchAndRunCommand(commands, canvas);
        }
    }

    private boolean startsWithRepeatCommand(List<String> commands) {
        return commands.get(0).startsWith(RepeatCommand.REPEAT_COMMAND);
    }

    private boolean containsRepeatCommand(List<String> commands) {
        return commands.stream().anyMatch(command -> command.startsWith(RepeatCommand.REPEAT_COMMAND));
    }

    private void searchAndRunCommand(List<String> commands, Canvas canvas) {
        for (String stringCommand : commands) {
            commandsHolder.findAndRunCommand(stringCommand, canvas);
        }
    }

}
