package de.cubenation.plugins.utils.permissionapi;

import org.bukkit.entity.Player;

public interface PermissionInterface {
    public boolean hasPermission(Player player, String permissionName);
}
