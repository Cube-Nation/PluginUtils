package de.cubenation.plugins.utils.exceptionapi;

import org.apache.commons.lang.Validate;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

/**
 * Will raise on player was not found or is not online.
 * 
 * @since 0.1.4
 */
public class PlayerNotFoundException extends PlayerException implements DefaultMessageableException {
    private static final long serialVersionUID = -5776382275660492845L;

    public PlayerNotFoundException(String playerName) {
        super("player " + playerName + " cannot be found", playerName);
    }

    @Override
    public String getLocaleMessage(ResourceConverter converter) {
        Validate.notNull(converter, "converter cannot be null");

        return converter.convert("exception.player.notFoundOrNotOnline", getPlayerName());
    }
}