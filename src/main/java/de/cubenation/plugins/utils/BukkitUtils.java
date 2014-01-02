package de.cubenation.plugins.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Useful methodes for Bukkit Server.
 * 
 * @since 1.2
 */
public class BukkitUtils {
    /**
     * Check if the player is online
     * 
     * @param playerName
     *            not case-sensitive player name
     * @return True, if the player is online, otherwise false. Also false if
     *         playerName is null or empty.
     * 
     * @since 1.2
     */
    public static boolean isPlayerOnline(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            return false;
        }

        Player player = Bukkit.getServer().getPlayerExact(playerName);
        if (player != null) {
            return true;
        }

        return false;
    }
}
