package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class WrapperManager {
    public static void setLogger(Logger logger) {
        if (isPluginEnabled("PermissionsEx")) {
            PermissionsExWrapper.setLogger(logger);
        }
        if (isPluginEnabled("VanishNoPacket")) {
            VanishNoPacketWrapper.setLogger(logger);
        }
        if (isPluginEnabled("LWC")) {
            LWCWrapper.setLogger(logger);
        }
        if (isPluginEnabled("WorldEdit")) {
            WorldEditWrapper.setLogger(logger);
        }
    }

    public static boolean isPluginEnabled(String pluginName) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName);
    }
}
