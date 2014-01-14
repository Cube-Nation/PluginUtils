package de.cubenation.plugins.utils.exceptionapi;

import org.apache.commons.lang.Validate;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

public class PluginNotEnabledException extends PluginException implements DefaultMessageableException {
    private static final long serialVersionUID = -7856851799777016425L;

    public PluginNotEnabledException(String pluginName) {
        super("plugin is not enabled", pluginName);
    }

    @Override
    public String getLocaleMessage(ResourceConverter converter) {
        Validate.notNull(converter, "converter cannot be null");

        return converter.convert("exception.plugin.notEnabled", getPluginName());
    }
}
