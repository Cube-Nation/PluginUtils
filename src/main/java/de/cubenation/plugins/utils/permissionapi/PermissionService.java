package de.cubenation.plugins.utils.permissionapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.permissionapi.PermissionInterface;
import de.cubenation.plugins.utils.permissionapi.wrapper.PermissionsExWrapper;

public class PermissionService implements PermissionInterface {
    public boolean hasPermission(Player player, String rightName) {
        boolean has = false;

        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
            has = PermissionsExWrapper.hasPermission(player, rightName);
        } else {
            if (player == null) {
                return false;
            }

            if (rightName == null || rightName.isEmpty()) {
                return false;
            }

            has = player.hasPermission(rightName);
        }
        return has;
    }
}
