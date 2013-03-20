package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class PermissionBroadcastTextAsynchron {
    public static void chat(JavaPlugin plugin, PermissionInterface permissionInterface, String message, String[] permissions) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            PermissionChatTextAsynchron.chat(plugin, permissionInterface, onlinePlayer, message, permissions);
        }
    }
}
