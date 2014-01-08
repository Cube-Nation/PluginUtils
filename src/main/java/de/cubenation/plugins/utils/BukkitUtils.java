package de.cubenation.plugins.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Useful methodes for Bukkit Server.
 * 
 * @since 0.1.4
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
     * @since 0.1.4
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

    /**
     * Returns a valid online player for a player name.
     * 
     * @param playerName
     *            not case-sensitive player name
     * @return valid online player
     * @throws IllegalStateException
     *             if player is not found or not online
     * 
     * @since 0.1.4
     */
    public static Player getPlayerByName(String playerName) throws IllegalStateException {
        Player player = Bukkit.getPlayerExact(playerName);
        if (player == null) {
            throw new IllegalStateException("player cannot be found");
        }

        return player;
    }

    /**
     * Returns a valid world for a player name. Where is player is currently
     * play.
     * 
     * @param playerName
     *            not case-sensitive player name
     * @return valid world
     * @throws IllegalStateException
     *             if player is not found or not online
     * @throws IllegalStateException
     *             if player has no world
     * @throws IllegalStateException
     *             if world is not registerd on server
     * 
     * @since 0.1.4
     */
    public static World getWorldByPlayerName(String playerName) throws IllegalStateException {
        World world = getPlayerByName(playerName).getWorld();
        if (world == null) {
            throw new IllegalStateException("player has no world");
        }

        World checkedWorld = Bukkit.getWorld(world.getName());
        if (checkedWorld == null) {
            throw new IllegalStateException("world is not registerd on server");
        }

        return world;
    }
}
