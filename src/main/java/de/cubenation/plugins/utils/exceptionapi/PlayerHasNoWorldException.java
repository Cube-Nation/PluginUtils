package de.cubenation.plugins.utils.exceptionapi;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

/**
 * Will raise on player has no world.
 * 
 * @since 0.1.4
 */
public class PlayerHasNoWorldException extends PlayerException {
    private static final long serialVersionUID = -8188422360259682682L;

    public PlayerHasNoWorldException(String playerName) {
        super("player has no world", playerName);
    }

    @Override
    public String getLocaleMessage(ResourceConverter converter) {
        return converter.convert("exception.player.hasNoWorld", getPlayerName());
    }
}