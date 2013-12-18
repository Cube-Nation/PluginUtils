package de.cubenation.plugins.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitUtils {
    public static boolean isPlayerOnline(String playerName) {
        Player player = Bukkit.getServer().getPlayer(playerName);
        if (player != null) {
            return true;
        }

        return false;
    }
}
