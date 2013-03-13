package de.cubenation.plugins.utils.commandapi.exception;

public class CommandExecutionException extends CommandException {
    private static final long serialVersionUID = 8842916434690301356L;

    public CommandExecutionException(Class<?> commandClass, String message, Throwable cause) {
        super("[" + (commandClass != null ? commandClass.getName() : "null") + "] " + message, cause);
    }
}
