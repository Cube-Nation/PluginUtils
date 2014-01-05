package de.cubenation.plugins.utils.pluginapi;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Util for use bukkit scheduler without plugin object.
 * 
 * @since 0.1.4
 */
public class ScheduleManager {
    private static HashMap<String, Plugin> registeredPlugins = new HashMap<String, Plugin>();

    /**
     * Register plugin object for later use.
     * 
     * @param plugin
     *            bukkit plugin
     * 
     * @since 0.1.4
     */
    public static void addPlugin(Plugin plugin) {
        registeredPlugins.put(plugin.getClass().getName(), plugin);
    }

    /**
     * Removes plugin object. Do this on plugin disabling.
     * 
     * @param plugin
     *            bukkit plugin
     * 
     * @since 0.1.4
     */
    public static void removePlugin(Plugin plugin) {
        registeredPlugins.remove(plugin.getClass().getName());
    }

    /**
     * Schedules a once off task to occur after a delay.<br>
     * <br>
     * This task will be executed by the main server thread.
     * 
     * @param task
     *            Task to be executed
     * @param delay
     *            Delay in server ticks before executing task
     * @return
     * @return Task id number (-1 if scheduling failed)
     * 
     * @since 0.1.4
     */
    public static int scheduleSyncDelayedTask(Runnable task, long delay) throws RuntimeException {
        Plugin plugin = getCurrentPlugin();

        if (plugin != null) {
            return Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task, delay);
        }
        return -1;
    }

    /**
     * Schedules a once off task to occur as soon as possible.<br>
     * <br>
     * This task will be executed by the main server thread.
     * 
     * @param task
     *            Task to be executed
     * @return Task id number (-1 if scheduling failed)
     * 
     * @since 0.1.4
     */
    public static int scheduleSyncDelayedTask(Runnable task) {
        Plugin plugin = getCurrentPlugin();

        if (plugin != null) {
            return Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task);
        }

        return -1;
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b> <br>
     * <br>
     * Returns a task that will run asynchronously.
     * 
     * @param task
     *            the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException
     *             if plugin is null
     * @throws IllegalArgumentException
     *             if task is null
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskAsynchronously(Runnable task) throws IllegalArgumentException {
        Plugin plugin = getCurrentPlugin();

        if (plugin != null) {
            return Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
        }

        return null;
    }

    /**
     * Returns a task that will run after the specified number of server ticks.
     * 
     * @param task
     *            the task to be run
     * @param delay
     *            the ticks to wait before running the task
     * @return
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException
     *             if plugin is null
     * @throws IllegalArgumentException
     *             if task is null
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskLater(Runnable task, long delay) throws IllegalArgumentException {
        Plugin plugin = getCurrentPlugin();

        if (plugin != null) {
            return Bukkit.getScheduler().runTaskLater(plugin, task, delay);
        }

        return null;
    }

    /**
     * Search for the current bukkit plugin.
     * 
     * @return current bukkit plugin
     * 
     * @since 0.1.4
     */
    public static Plugin getCurrentPlugin() {
        StackTraceElement[] stackTraceArray = new Throwable().getStackTrace();

        boolean firstIgnored = false;
        for (StackTraceElement caller : stackTraceArray) {
            if (!firstIgnored) {
                firstIgnored = true;
                continue;
            }

            Class<?> callerClass;
            try {
                callerClass = Class.forName(caller.getClassName());
            } catch (ClassNotFoundException e) {
                continue;
            }

            String foundPluginName = null;
            Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
            for (Plugin plugin : plugins) {
                if (plugin.getClass().getClassLoader() == callerClass.getClassLoader()) {
                    foundPluginName = plugin.getClass().getName();
                    break;
                }
            }

            if (foundPluginName == null) {
                continue;
            }

            if (!registeredPlugins.containsKey(foundPluginName)) {
                continue;
            }

            Plugin plugin = registeredPlugins.get(foundPluginName);
            if (plugin == null) {
                continue;
            }

            if (caller.getClassName().equals(ScheduleManager.class.getName())) {
                continue;
            }

            return plugin;
        }

        throw null;
    }
}
