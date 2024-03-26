package edu.mroz.utils;

import edu.mroz.components.Console;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogAppender {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static ConsoleLogAppender instance;
    @Setter
    private Console console;

    private ConsoleLogAppender() {
    }

    public static synchronized ConsoleLogAppender getInstance() {
        if (instance == null) {
            instance = new ConsoleLogAppender();
        }
        return instance;
    }

    public void clearLogs() {
        final JTextPane textPane = (JTextPane) console.getLogArea().getViewport().getView();
        try {
            StyledDocument document = textPane.getStyledDocument();
            document.remove(0, document.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUserPromptLog(String logInfo) {
        SimpleAttributeSet attribute = new SimpleAttributeSet();
        StyleConstants.setForeground(attribute, Color.BLUE);
        String formattedLog = ">>> [" + LocalDateTime.now().format(formatter) + "]: " + logInfo + "\n";
        try {
            final JTextPane textPane = (JTextPane) console.getLogArea().getViewport().getView();
            textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), formattedLog, attribute);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void addInfoSystemLog(String logInfo) {
        SimpleAttributeSet attribute = new SimpleAttributeSet();
        StyleConstants.setForeground(attribute, Color.decode("#ad61e0"));
        String formattedLog = getSystemFormattedMessage(logInfo);
        try {
            final JTextPane textPane = (JTextPane) console.getLogArea().getViewport().getView();
            textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), formattedLog, attribute);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void addErrorSystemLog(String logInfo) {
        SimpleAttributeSet attribute = new SimpleAttributeSet();
        StyleConstants.setForeground(attribute, Color.RED);
        String formattedLog = getSystemFormattedMessage(logInfo);
        try {
            final JTextPane textPane = (JTextPane) console.getLogArea().getViewport().getView();
            textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), formattedLog, attribute);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private String getSystemFormattedMessage(String log) {
        return "<<< [" + LocalDateTime.now().format(formatter) + "]: " + log + "\n";
    }

}
