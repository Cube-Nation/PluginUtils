package de.cubenation.plugins.utils.chatapi;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

import de.cubenation.plugins.utils.PluginUtils;
import de.cubenation.plugins.utils.exceptionapi.DefaultMessageableException;
import de.cubenation.plugins.utils.exceptionapi.MessageableException;
import de.cubenation.plugins.utils.wrapperapi.WrapperManager;

public class ResourceConverter {
    private final ResourceBundle resource;

    public ResourceConverter(ResourceBundle resource) {
        this.resource = resource;
    }

    public String convert(String resourceString, Object... parameter) {
        if (resourceString == null || resourceString.isEmpty()) {
            return "";
        }

        LinkedList<String> strList = new LinkedList<String>();

        LinkedList<Object> paramList = new LinkedList<Object>();
        for (Object param : parameter) {
            if (param instanceof DefaultMessageableException) {
                if (WrapperManager.isPluginEnabled(WrapperManager.PLUGIN_NAME_PLUGIN_UTILS)) {
                    PluginUtils plugin = (PluginUtils) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.PLUGIN_NAME_PLUGIN_UTILS);

                    paramList.add(((MessageableException) param).getLocaleMessage(plugin));
                } else {
                    paramList.add(param);
                }
            } else if (param instanceof MessageableException) {
                paramList.add(((MessageableException) param).getLocaleMessage(this));
            } else {
                paramList.add(param);
            }
        }
        parameter = paramList.toArray();

        String convertString = "";
        try {
            convertString = resource.getString(resourceString);
        } catch (MissingResourceException e) {
            Logger.getLogger(PluginUtils.class.getName()).log(Level.WARNING, "error on send chat message", e);
            return "";
        }

        if (parameter.length > 0) {
            MessageFormat formatter = new MessageFormat("");
            formatter.setLocale(resource.getLocale());
            String outputString = ColorParser.replaceColor(convertString);
            formatter.applyPattern(outputString);
            String formatedOutputString = formatter.format(parameter);

            formatedOutputString = formatedOutputString.replace("\r\n", "\n").replace("\r", "\n");
            if (formatedOutputString.contains("\n")) {
                for (String msg : formatedOutputString.split("\n")) {
                    if (msg.trim().isEmpty()) {
                        continue;
                    }
                    strList.add(msg);
                }
            } else {
                strList.add(formatedOutputString);
            }
        } else {
            String outputString = ColorParser.replaceColor(convertString);

            outputString = outputString.replace("\r\n", "\n").replace("\r", "\n");
            if (outputString.contains("\n")) {
                for (String msg : outputString.split("\n")) {
                    if (msg.trim().isEmpty()) {
                        continue;
                    }
                    strList.add(msg);
                }
            } else {
                strList.add(outputString);
            }
        }

        return StringUtils.join(strList, "\n");
    }
}
