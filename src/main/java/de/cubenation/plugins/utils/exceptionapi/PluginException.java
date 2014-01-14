package de.cubenation.plugins.utils.exceptionapi;

public abstract class PluginException extends MessageableException {
    private static final long serialVersionUID = -2791881277448769410L;

    private final String pluginName;

    public PluginException(String message, String pluginName) {
        super(message);

        this.pluginName = pluginName;
    }

    public String getPluginName() {
        return pluginName;
    }
}
