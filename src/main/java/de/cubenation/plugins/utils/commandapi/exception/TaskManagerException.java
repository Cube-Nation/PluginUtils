package de.cubenation.plugins.utils.commandapi.exception;

import de.cubenation.plugins.utils.commandapi.TaskManager;

/**
 * An RuntimeException that throws by {@link TaskManager}.
 * 
 * @since 0.1.4
 */
public class TaskManagerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskManagerException(String message) {
        super(message);
    }
}
