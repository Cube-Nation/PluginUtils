package de.cubenation.plugins.utils.chatapi.Chatter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.cubenation.plugins.utils.chatapi.ResourceConverter;
import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class PermissionBroadcastResourceAsynchron {
    public static void chat(Plugin plugin, ResourceConverter converter, PermissionInterface permissionInterface, String resourceString, String[] permissions,
            Object... parameter) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            PermissionChatResourceAsynchron.chat(plugin, converter, permissionInterface, onlinePlayer, resourceString, permissions, parameter);
        }
    }
}
