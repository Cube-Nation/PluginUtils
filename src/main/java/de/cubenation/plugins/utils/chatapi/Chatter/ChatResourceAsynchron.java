package de.cubenation.plugins.utils.chatapi.Chatter;

import java.util.MissingResourceException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;

public class ChatResourceAsynchron {
    public static void chat(final Plugin plugin, final ResourceConverter converter, final CommandSender sender, final String resourceString,
            final Object... parameter) {
        if (sender == null) {
            plugin.getLogger().warning("sender is null");
            return;
        }

        if (resourceString == null || resourceString.isEmpty()) {
            return;
        }

        Bukkit.getScheduler().runTask(plugin, new Thread("ChatService->ChatResourceAsynchron") {
            @Override
            public void run() {
                if (converter == null) {
                    plugin.getLogger().severe("i18n support is disabled");
                    return;
                }

                String convertedStr = converter.convert(resourceString, parameter);
                try {
                    for (String msg : convertedStr.split("\n")) {
                        if (msg.trim().isEmpty()) { // do not send empty lines
                            continue;
                        }
                        sender.sendMessage(msg);
                    }
                } catch (NullPointerException e) {
                    plugin.getLogger().log(Level.SEVERE, "error on ChatService->ChatResourceAsynchron(" + sender.getName() + ", " + resourceString + ")", e);
                } catch (MissingResourceException e) {
                    plugin.getLogger().log(Level.SEVERE, "error on ChatService->ChatResourceAsynchron(" + sender.getName() + ", " + resourceString + ")", e);
                } catch (ClassCastException e) {
                    plugin.getLogger().log(Level.SEVERE, "error on ChatService->ChatResourceAsynchron(" + sender.getName() + ", " + resourceString + ")", e);
                }
            }
        });
    }
}
