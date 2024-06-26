package edu.mroz.interpreter.commands.utils;

import edu.mroz.utils.ConsoleLogAppender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandErrorHandler {

    private CommandErrorHandler() {
    }

    private static final ConsoleLogAppender consoleLogAppender = ConsoleLogAppender.getInstance();

    public static void handleError(Pattern pattern, String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find() && matcher.group(1) != null && matcher.group(2) != null && !matcher.group(2).isBlank()) {
            String errorLogString = "Expected proper " + matcher.group(1) + " command. Value=" + matcher.group(2) + " is unexpected.";
            consoleLogAppender.addErrorSystemLog(errorLogString);
        } else {
            String errorLogString = "Command: " + value + " is unexpected.";
            consoleLogAppender.addErrorSystemLog(errorLogString);
        }
    }

    public static void handleCircleCommandError(String value, String reason) {
        String errorLogString = "Issue when trying to execute circle command: " + value + ". \n" + reason;
        consoleLogAppender.addErrorSystemLog(errorLogString);
    }

}
