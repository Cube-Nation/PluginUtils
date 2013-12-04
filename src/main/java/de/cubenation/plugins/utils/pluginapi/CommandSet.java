package de.cubenation.plugins.utils.pluginapi;

public class CommandSet {
    private Class<?> clazz;
    private Object[] parameters;

    public CommandSet(Class<?> clazz, Object... parameters) {
        this.clazz = clazz;
        this.parameters = parameters;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
