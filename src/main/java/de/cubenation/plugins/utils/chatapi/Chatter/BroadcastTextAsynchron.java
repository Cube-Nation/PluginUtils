package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastTextAsynchron {
    public static void chat(JavaPlugin plugin, String message) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            ChatTextAsynchron.chat(plugin, onlinePlayer, message);
        }
    }
}
