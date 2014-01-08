package de.cubenation.plugins.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.exceptionapi.PlayerHasNoWorldException;
import de.cubenation.plugins.utils.exceptionapi.PlayerNotFoundException;
import de.cubenation.plugins.utils.exceptionapi.WorldNotFoundException;

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
     * Returns a valid online player for a player name. On not found it will
     * raise an PlayerNotFoundException.
     * 
     * @param playerName
     *            not case-sensitive player name
     * @return valid online player
     * @throws PlayerNotFoundException
     *             if player is not found or not online
     * 
     * @since 0.1.4
     */
    public static Player getPlayerByName(String playerName) throws PlayerNotFoundException {
        Validate.notEmpty(playerName, "player name cannot be null or empty");

        Player player = Bukkit.getPlayerExact(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(playerName);
        }

        return player;
    }

    /**
     * Returns a valid world for a world name. On not found it will raise an
     * WorldNotFoundException.
     * 
     * @param worldName
     *            not case-sensitive world name
     * @return valid world
     * @throws WorldNotFoundException
     *             if world is not registerd on server
     */
    public static World getWorldByName(String worldName) throws WorldNotFoundException {
        Validate.notEmpty(worldName, "world name cannot be null or empty");

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            throw new WorldNotFoundException(worldName);
        }

        return world;
    }

    /**
     * Returns a valid world for a player name. Where is player is currently
     * play. On not found it will raise a PlayerNotFoundException,
     * WorldNotFoundException or PlayerHasNoWorldException.
     * 
     * @param playerName
     *            not case-sensitive player name
     * @return valid world
     * @throws PlayerNotFoundException
     *             if player is not found or not online
     * @throws WorldNotFoundException
     *             if world is not registerd on server
     * @throws PlayerHasNoWorldException
     *             if player has no world
     * 
     * @since 0.1.4
     */
    public static World getWorldByPlayerName(String playerName) throws PlayerNotFoundException, WorldNotFoundException, PlayerHasNoWorldException {
        Validate.notEmpty(playerName, "player name cannot be null or empty");

        World world = getPlayerByName(playerName).getWorld();
        if (world == null) {
            throw new PlayerHasNoWorldException(playerName);
        }

        return getWorldByName(world.getName());
    }
}
