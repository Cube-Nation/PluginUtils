package de.cubenation.plugins.utils.chatapi.Chatter;

import java.util.ResourceBundle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastResourceAsynchron {
    public static void chat(JavaPlugin plugin, ResourceBundle resource, String resourceString, Object... parameter) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            ChatResourceAsynchron.chat(plugin, resource, onlinePlayer, resourceString, parameter);
        }
    }
}
