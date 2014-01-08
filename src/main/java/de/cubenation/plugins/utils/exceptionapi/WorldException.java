package de.cubenation.plugins.utils.exceptionapi;

/**
 * Super class for all world exception.
 * 
 * @since 0.1.4
 */
public abstract class WorldException extends MessageableException {
    private static final long serialVersionUID = 5923209673221758907L;

    private final String worldName;

    public WorldException(String message, String worldName) {
        super(message);

        this.worldName = worldName;
    }

    public String getWorldName() {
        return worldName;
    }
}