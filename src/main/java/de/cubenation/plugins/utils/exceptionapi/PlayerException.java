package de.cubenation.plugins.utils.exceptionapi;

/**
 * Super class for all player exception.
 * 
 * @since 0.1.4
 */
public abstract class PlayerException extends DefaultMessageableException {
    private static final long serialVersionUID = 4101207331381341559L;

    private final String playerName;

    public PlayerException(String message, String playerName) {
        super(message);

        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}