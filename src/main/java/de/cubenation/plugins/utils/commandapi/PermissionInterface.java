package de.cubenation.plugins.utils.commandapi;

import org.bukkit.entity.Player;

public interface PermissionInterface {
    public boolean hasPermission(Player player, String permissionName);
}
