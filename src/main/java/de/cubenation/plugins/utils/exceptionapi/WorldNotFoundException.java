package de.cubenation.plugins.utils.exceptionapi;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

/**
 * Will raise on world not registered on server.
 * 
 * @since 0.1.4
 */
public class WorldNotFoundException extends WorldException {
    private static final long serialVersionUID = 8644075630695257338L;

    public WorldNotFoundException(String worldName) {
        super("world is not registerd on server", worldName);
    }

    @Override
    public String getLocaleMessage(ResourceConverter converter) {
        return converter.convert("exception.world.notFound", getWorldName());
    }
}