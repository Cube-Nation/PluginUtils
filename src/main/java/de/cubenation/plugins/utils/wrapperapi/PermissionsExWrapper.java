package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PermissionsExWrapper {
    private static ru.tehkode.permissions.PermissionManager permissionManager;
    private static Logger log;

    public static void setLogger(Logger log) {
        PermissionsExWrapper.log = log;
    }

    public static void loadPlugin() {
        if (permissionManager == null) {
            ru.tehkode.permissions.bukkit.PermissionsEx permissions = (ru.tehkode.permissions.bukkit.PermissionsEx) Bukkit.getServer().getPluginManager()
                    .getPlugin("PermissionsEx");
            if (permissions == null) {
                log.info("PermissionsEx not found");
                permissionManager = null;
                return;
            }
            permissionManager = ru.tehkode.permissions.bukkit.PermissionsEx.getPermissionManager();
        }
    }

    public static boolean hasPermission(Player player, String rightName) {
        if (permissionManager == null) {
            loadPlugin();
        }

        if (permissionManager != null) {
            return permissionManager.has(player, rightName);
        }
        return false;
    }
}
