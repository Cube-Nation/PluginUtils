package de.cubenation.plugins.utils.wrapperapi;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishManager;
import org.kitteh.vanish.VanishPlugin;

public class VanishNoPacketWrapper {
    private static VanishManager vanishManager = null;
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

    public static VanishManager loadPlugin() {
        if (vanishManager == null) {
            VanishPlugin vanishPlugin = (VanishPlugin) Bukkit.getServer().getPluginManager().getPlugin("VanishNoPacket");
            if (vanishPlugin == null) {
                log.info("VanishPlugin not found");
                return null;
            }
            vanishManager = vanishPlugin.getManager();
        }
        return vanishManager;
    }
}
