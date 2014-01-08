package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

public class ChatResourceSynchron {
    public static void chat(Plugin plugin, ResourceConverter converter, CommandSender sender, String resourceString, Object... parameter) {
        if (sender == null) {
            plugin.getLogger().warning("sender is null");
            return;
        }

        if (resourceString == null || resourceString.isEmpty()) {
            return;
        }

        if (converter == null) {
            plugin.getLogger().severe("i18n support is disabled");
            return;
        }

        String convertedStr = converter.convert(resourceString, parameter);
        for (String msg : convertedStr.split("\n")) {
            if (msg.trim().isEmpty()) { // do not send empty lines
                continue;
            }
            sender.sendMessage(msg);
        }
    }
}
