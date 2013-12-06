package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class WrapperManager {
    public enum Plugins {
        DYNMAP("dynmap"), LOG_BLOCK("LogBlock"), LWC("LWC"), PERMISSIONS_EX("PermissionsEx"), SHOPKEEPERS("Shopkeepers"), VANISH_NO_PACKET("VanishNoPacket"), VAULT(
                "Vault"), WORLD_EDIT("WorldEdit"), WORLD_GUARD("WorldGuard");
        String name;

        Plugins(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void setLogger(Logger logger) {
        if (isPluginEnabled(Plugins.DYNMAP)) {
            DynMapWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.LOG_BLOCK)) {
            LogBlockWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.LWC)) {
            LWCWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.PERMISSIONS_EX)) {
            PermissionsExWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.SHOPKEEPERS)) {
            ShopkeepersWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.VANISH_NO_PACKET)) {
            VanishNoPacketWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.VAULT)) {
            VaultWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.WORLD_EDIT)) {
            WorldEditWrapper.setLogger(logger);
        }
        if (isPluginEnabled(Plugins.WORLD_GUARD)) {
            WorldGuardWrapper.setLogger(logger);
        }
    }

    public static boolean isPluginEnabled(Plugins plugin) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(plugin.getName());
    }
}
