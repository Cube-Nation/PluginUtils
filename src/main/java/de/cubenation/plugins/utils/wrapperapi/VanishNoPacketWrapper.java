package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VanishNoPacketWrapper {
    private static org.kitteh.vanish.VanishManager vanishManager = null;
    private static Logger log;

    public static void setLogger(Logger log) {
        VanishNoPacketWrapper.log = log;
    }

    public static boolean isVanished(Player player) {
        if (vanishManager == null) {
            loadPlugin();
        }

        if (vanishManager != null) {
            return vanishManager.isVanished(player);
        }
        return false;
    }

    public static void loadPlugin() {
        if (vanishManager == null) {
            org.kitteh.vanish.VanishPlugin vanishPlugin = (org.kitteh.vanish.VanishPlugin) Bukkit.getServer().getPluginManager()
                    .getPlugin(WrapperManager.PLUGIN_NAME_VANISH_NO_PACKET);
            if (vanishPlugin == null) {
                log.info(WrapperManager.PLUGIN_NAME_VANISH_NO_PACKET + " not found");
                vanishManager = null;
                return;
            }
            vanishManager = vanishPlugin.getManager();
        }
    }
}
