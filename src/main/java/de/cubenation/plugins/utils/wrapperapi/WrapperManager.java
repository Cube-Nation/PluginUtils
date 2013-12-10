package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class WrapperManager {
    public static final String PLUGIN_NAME_DYNMAP = "dynmap";
    public static final String PLUGIN_NAME_LOG_BLOCK = "LogBlock";
    public static final String PLUGIN_NAME_LWC = "LWC";
    public static final String PLUGIN_NAME_PERMISSIONS_EX = "PermissionsEx";
    public static final String PLUGIN_NAME_SHOPKEEPERS = "Shopkeepers";
    public static final String PLUGIN_NAME_VANISH_NO_PACKET = "VanishNoPacket";
    public static final String PLUGIN_NAME_VAULT = "Vault";
    public static final String PLUGIN_NAME_WORLD_EDIT = "WorldEdit";
    public static final String PLUGIN_NAME_WORLD_GUARD = "WorldGuard";

    public static void setLogger(Logger logger) {
        if (isPluginEnabled(PLUGIN_NAME_DYNMAP)) {
            DynMapWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_LOG_BLOCK)) {
            LogBlockWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_LWC)) {
            LWCWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_PERMISSIONS_EX)) {
            PermissionsExWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_SHOPKEEPERS)) {
            ShopkeepersWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_VANISH_NO_PACKET)) {
            VanishNoPacketWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_VAULT)) {
            VaultWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_WORLD_EDIT)) {
            WorldEditWrapper.setLogger(logger);
        }
        if (isPluginEnabled(PLUGIN_NAME_WORLD_GUARD)) {
            WorldGuardWrapper.setLogger(logger);
        }
    }

    public static boolean isPluginEnabled(String pluginName) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName);
    }
}
