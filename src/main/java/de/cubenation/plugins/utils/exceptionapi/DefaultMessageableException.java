package de.cubenation.plugins.utils.exceptionapi;

public abstract class DefaultMessageableException extends MessageableException {
    private static final long serialVersionUID = -1449562408963707564L;

    public DefaultMessageableException(String message) {
        super(message);
    }
}
