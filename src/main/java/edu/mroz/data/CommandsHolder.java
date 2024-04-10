package edu.mroz.data;

import edu.mroz.components.Canvas;
import edu.mroz.interpreter.commands.*;
import edu.mroz.utils.ConsoleLogAppender;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CommandsHolder {

    private static final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();
    private static CommandsHolder instance;
    @Getter
    private final List<Command> commandList;

    public static synchronized CommandsHolder getInstance() {
        if (instance == null) {
            instance = new CommandsHolder();
        }
        return instance;
    }

    public String getCommandsRegexToSplit() {
        StringBuilder builder = new StringBuilder();
        builder.append(" (?=");
        for (int i = 0; i < commandList.size(); i++) {
            builder.append(commandList.get(i).getCommandName());
            if (i != commandList.size() - 1) {
                builder.append("|");
            }
        }
        builder.append(")");
        return builder.toString();
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

    private CommandsHolder() {
        commandList = new ArrayList<>();
        commandList.add(new GoCommand());
        commandList.add(new DirectionCommand());
        commandList.add(new ColorCommand());
        commandList.add(new UpCommand());
        commandList.add(new DownCommand());
        commandList.add(new RollbackCommand());
        commandList.add(new CircleCommand());
        commandList.add(new SizeCommand());
        commandList.add(new StyleCommand());
    }

}
