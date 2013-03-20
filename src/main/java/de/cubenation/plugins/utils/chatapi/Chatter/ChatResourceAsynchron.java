package de.cubenation.plugins.utils.chatapi.Chatter;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.ColorParser;

public class ChatResourceAsynchron {
    public static void chat(final JavaPlugin plugin, final ResourceBundle resource, final Player player, final String resourceString, final Object... parameter) {
        if (player == null) {
            throw new NullPointerException("player is null");
        }

        if (resourceString == null || resourceString.isEmpty()) {
            return;
        }

        Bukkit.getScheduler().runTask(plugin, new Thread("ChatService->ChatResourceAsynchron") {
            @Override
            public void run() {
                if (resource == null) {
                    plugin.getLogger().severe("i18n support is disabled");
                    return;
                }

                try {
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
                                player.sendMessage(msg);
                            }
                        } else {
                            player.sendMessage(formatedOutputString);
                        }
                    } else {
                        String outputString = ColorParser.replaceColor(resource.getString(resourceString));

                        outputString = outputString.replace("\r\n", "\n").replace("\r", "\n");
                        if (outputString.contains("\n")) {
                            for (String msg : outputString.split("\n")) {
                                if (msg.trim().isEmpty()) {
                                    continue;
                                }
                                player.sendMessage(msg);
                            }
                        } else {
                            player.sendMessage(outputString);
                        }
                    }
                } catch (NullPointerException e) {
                    plugin.getLogger().log(Level.SEVERE, "error on ChatService->ChatResourceAsynchron(" + player.getName() + ", " + resourceString + ")", e);
                } catch (MissingResourceException e) {
                    plugin.getLogger().log(Level.SEVERE, "error on ChatService->ChatResourceAsynchron(" + player.getName() + ", " + resourceString + ")", e);
                } catch (ClassCastException e) {
                    plugin.getLogger().log(Level.SEVERE, "error on ChatService->ChatResourceAsynchron(" + player.getName() + ", " + resourceString + ")", e);
                }
            }
        });
    }
}
