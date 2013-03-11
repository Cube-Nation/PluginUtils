package de.cubenation.plugins.utils.commandapi.exception;

public class CommandWarmUpException extends CommandException {
    private static final long serialVersionUID = 8842916434690301356L;

    public CommandWarmUpException() {
        super();
    }

    public CommandWarmUpException(String message) {
        super(message);
    }

    public CommandWarmUpException(Throwable cause) {
        super(cause);
    }

    public CommandWarmUpException(Class<?> commandClass, String message) {
        super("[" + (commandClass != null ? commandClass.getName() : "null") + "] " + message);
    }

    public CommandWarmUpException(Class<?> commandClass, String message, Throwable cause) {
        super("[" + (commandClass != null ? commandClass.getName() : "null") + "] " + message, cause);
    }
}
