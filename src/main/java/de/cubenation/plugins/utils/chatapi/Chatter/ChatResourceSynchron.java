package de.cubenation.plugins.utils.chatapi.Chatter;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.ColorParser;

public class ChatResourceSynchron {
    public static void chat(JavaPlugin plugin, ResourceBundle resource, CommandSender sender, String resourceString, Object... parameter) {
        if (sender == null) {
            plugin.getLogger().warning("sender is null");
            return;
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
            String outputString = ColorParser.replaceColor(resource.getString(resourceString));
            formatter.applyPattern(outputString);
            String formatedOutputString = formatter.format(parameter);

            formatedOutputString = formatedOutputString.replace("\r\n", "\n").replace("\r", "\n");

            if (formatedOutputString.contains("\n")) {
                for (String msg : formatedOutputString.split("\n")) {
                    if (msg.trim().isEmpty()) {
                        continue;
                    }
                    sender.sendMessage(msg);
                }
            } else {
                sender.sendMessage(formatedOutputString);
            }
        } else {
            String outputString = resource.getString(resourceString);

            outputString = outputString.replace("\r\n", "\n").replace("\r", "\n");

            if (outputString.contains("\n")) {
                for (String msg : outputString.split("\n")) {
                    if (msg.trim().isEmpty()) {
                        continue;
                    }
                    sender.sendMessage(msg);
                }
            } else {
                sender.sendMessage(outputString);
            }
        }
    }
}
