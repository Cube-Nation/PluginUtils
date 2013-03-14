package de.cubenation.plugins.utils.commandapi.exception;

public class CommandException extends Exception {
    private static final long serialVersionUID = 870638193072101739L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException() {
        super();
    }
}
