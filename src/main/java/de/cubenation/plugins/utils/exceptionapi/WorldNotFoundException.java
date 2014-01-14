package de.cubenation.plugins.utils.exceptionapi;

import org.apache.commons.lang.Validate;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

/**
 * Will raise on world not registered on server.
 * 
 * @since 0.1.4
 */
public class WorldNotFoundException extends WorldException implements DefaultMessageableException {
    private static final long serialVersionUID = 8644075630695257338L;

    public WorldNotFoundException(String worldName) {
        super("world is not registerd on server", worldName);
    }

    @Override
    public String getLocaleMessage(ResourceConverter converter) {
        Validate.notNull(converter, "converter cannot be null");

        return converter.convert("exception.world.notFound", getWorldName());
    }
}