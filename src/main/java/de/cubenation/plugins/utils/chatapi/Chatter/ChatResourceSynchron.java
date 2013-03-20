package de.cubenation.plugins.utils.chatapi.Chatter;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.ColorParser;
import de.cubenation.plugins.utils.chatapi.UTF8Converter;

public class ChatResourceSynchron {
    public static void chat(JavaPlugin plugin, ResourceBundle resource, Player player, String resourceString, Object... parameter) {
        if (player == null) {
            throw new NullPointerException("player is null");
        }

        if (resourceString == null || resourceString.isEmpty()) {
            return;
        }

        if (resource == null) {
            plugin.getLogger().severe("i18n support is disabled");
            return;
        }

        if (parameter.length > 0) {
            MessageFormat formatter = new MessageFormat("");
            formatter.setLocale(resource.getLocale());
            String outputString = ColorParser.replaceColor(UTF8Converter.convert(resource.getString(resourceString)));
            formatter.applyPattern(outputString);
            String formatedOutputString = formatter.format(parameter);
            player.sendMessage(formatedOutputString);
        } else {
            String outputString = UTF8Converter.convert(resource.getString(resourceString));
            player.sendMessage(outputString);
        }
    }
}
