package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsExWrapper {
    private static PermissionManager permissionManager;
    private static Logger log;

    public static void setLogger(Logger log) {
        PermissionsExWrapper.log = log;
    }

    public static PermissionManager loadPlugin() {
        if (permissionManager == null) {
            PermissionsEx permissions = (PermissionsEx) Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx");
            if (permissions == null) {
                log.info("PermissionsEx not found");
                return null;
            }
            permissionManager = PermissionsEx.getPermissionManager();
        }
        return permissionManager;
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
