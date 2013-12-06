package de.cubenation.plugins.utils.permissionapi;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.wrapperapi.PermissionsExWrapper;
import de.cubenation.plugins.utils.wrapperapi.WrapperManager;

public class PermissionService implements PermissionInterface {
    public boolean hasPermission(Player player, String rightName) {
        boolean has = false;

        if (WrapperManager.isPluginEnabled(WrapperManager.Plugins.PERMISSIONS_EX)) {
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
