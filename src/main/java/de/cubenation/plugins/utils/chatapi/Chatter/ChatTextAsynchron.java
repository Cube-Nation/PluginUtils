package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatTextAsynchron {
    public static void chat(final JavaPlugin plugin, final Player player, final String message) {
        if (player == null) {
            throw new NullPointerException("player is null");
        }

        if (message == null || message.isEmpty()) {
            return;
        }

        Bukkit.getScheduler().runTask(plugin, new Thread("ChatService->ChatTextAsynchron") {
            @Override
            public void run() {
                player.sendMessage(message);
            }
        });
    }
}
