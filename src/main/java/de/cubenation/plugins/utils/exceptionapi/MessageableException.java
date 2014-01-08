package de.cubenation.plugins.utils.exceptionapi;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

public abstract class MessageableException extends Exception {
    private static final long serialVersionUID = 9157000101809549692L;

    public MessageableException(String message) {
        super(message);
    }

    public abstract String getLocaleMessage(ResourceConverter converter);
}
