package de.cubenation.plugins.utils.chatapi.Chatter;

import java.util.ResourceBundle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class PermissionBroadcastResourceAsynchron {
    public static void chat(JavaPlugin plugin, ResourceBundle resource, PermissionInterface permissionInterface, String resourceString, String[] permissions,
            Object... parameter) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            PermissionChatResourceAsynchron.chat(plugin, resource, permissionInterface, onlinePlayer, resourceString, permissions, parameter);
        }
    }
}
