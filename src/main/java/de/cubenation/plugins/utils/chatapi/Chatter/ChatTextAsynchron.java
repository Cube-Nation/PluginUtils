package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatTextAsynchron {
    public static void chat(final JavaPlugin plugin, final CommandSender sender, final String message) {
        if (sender == null) {
            plugin.getLogger().warning("sender is null");
            return;
        }

        if (message == null || message.isEmpty()) {
            return;
        }

        Bukkit.getScheduler().runTask(plugin, new Thread("ChatService->ChatTextAsynchron") {
            @Override
            public void run() {
                String messageNew = message.replace("\r\n", "\n").replace("\r", "\n");
                if (messageNew.contains("\n")) {
                    for (String msg : messageNew.split("\n")) {
                        if (msg.trim().isEmpty()) {
                            continue;
                        }
                        sender.sendMessage(msg);
                    }
                } else {
                    sender.sendMessage(message);
                }
            }
        });
    }
}
