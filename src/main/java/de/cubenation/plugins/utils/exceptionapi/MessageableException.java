package de.cubenation.plugins.utils.exceptionapi;

import org.apache.commons.lang.Validate;

import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.chatapi.ResourceConverter;
import de.cubenation.plugins.utils.pluginapi.BasePlugin;

public abstract class MessageableException extends Exception {
    private static final long serialVersionUID = 9157000101809549692L;

    public MessageableException(String message) {
        super(message);
    }

    public MessageableException(String message, Throwable cause) {
        super(message, cause);
    }

    public final String getLocaleMessage(BasePlugin plugin) {
        Validate.notNull(plugin, "plugin cannot be null");

        return getLocaleMessage(plugin.getChatService());
    }

    public final String getLocaleMessage(ChatService chatService) {
        Validate.notNull(chatService, "chat service cannot be null");

        return getLocaleMessage(chatService.getResourceConverter());
    }

    public abstract String getLocaleMessage(ResourceConverter converter);
}
