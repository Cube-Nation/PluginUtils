package de.cubenation.plugins.utils.commandapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import de.cubenation.plugins.utils.commandapi.exception.TaskManagerException;

/**
 * Thread thats atached in bukkit task queue must be <b>high</b> performant, so
 * the server do not lags. It is recommended to run threads that do not need
 * access to bukkit api asynchronous from bukkit task queue (e.g. database
 * access).<br>
 * <br>
 * The {@link TaskManager} offers you the way to easy swich between sync an
 * async a thread. It is automatically supports by CommandApi and PluginApi.<br>
 * <br>
 * Bukkit events are not supported, they <b>must</b> be run in bukkit task queue
 * for possibly cancel event.
 * 
 * @since 0.1.4
 */
public class TaskManager {
    private static ThreadLocal<CommandTask> commandTaskPlugins = new ThreadLocal<CommandTask>();

    /**
     * Atache the current thread to bukkit task queue and run on the next tick.
     * 
     * 
     * @throws TaskManagerException
     *             Is thown when current thread was not created with
     *             {@link #createTask(JavaPlugin)}.
     * @see BukkitScheduler#runTask(org.bukkit.plugin.Plugin, Runnable)
     * @since 0.1.4
     */
    public static void syncTask() throws TaskManagerException {
        CommandTask commandTask = commandTaskPlugins.get();

        if (commandTask == null) {
            throw new TaskManagerException("Thread was not created by " + TaskManager.class.getSimpleName() + " or Plugin was not set for "
                    + CommandsManager.class.getSimpleName());
        }

        commandTask.syncronTask();
    }

    /**
     * Detache the current thread from bukkit task queue and run parallel on.
     * 
     * @throws TaskManagerException
     *             Is thown when current thread was not created with
     *             {@link #createTask(JavaPlugin)}.
     * @since 0.1.4
     */
    public static void asyncTask() throws TaskManagerException {
        CommandTask commandTask = commandTaskPlugins.get();

        if (commandTask == null) {
            throw new TaskManagerException("Thread was not created by " + TaskManager.class.getSimpleName() + " or Plugin was not set for "
                    + CommandsManager.class.getSimpleName());
        }

        commandTask.asyncronTask();
    }

    /**
     * Same as {@link #syncTask()} but thrown no exception, if the current the
     * can not be used in {@link TaskManager}. Use this if you're not sure
     * whether the thread was created using {@link #createTask(JavaPlugin)}.
     * 
     * @since 0.1.4
     */
    public static void syncTaskSilent() {
        try {
            syncTask();
        } catch (TaskManagerException e) {
        }
    }

    /**
     * Same as {@link #asyncTask()} but thrown no exception, if the current the
     * can not be used in {@link TaskManager}. Use this if you're not sure
     * whether the thread was created using {@link #createTask(JavaPlugin)}.
     * 
     * @since 0.1.4
     */
    public static void asyncTaskSilent() {
        try {
            asyncTask();
        } catch (TaskManagerException e) {
        }
    }

    /**
     * Prepare the Thread for used by {@link TaskManager}. The calling thread
     * must be started asynchronously with
     * {@link BukkitScheduler#runTaskAsynchronously(org.bukkit.plugin.Plugin, Runnable)}
     * or
     * {@link BukkitScheduler#runTaskTimerAsynchronously(org.bukkit.plugin.Plugin, Runnable, long, long)}
     * .
     * 
     * @param plugin
     *            The bukkit plugin that created the thread.
     * @throws TaskManagerException
     *             Is thown when parameter plugin is null.
     * @since 0.1.4
     */
    public static void createTask(JavaPlugin plugin) throws TaskManagerException {
        if (plugin == null) {
            throw new TaskManagerException("Plugin could not be null");
        }

        CommandTask commandTask = commandTaskPlugins.get();
        if (commandTask == null) {
            commandTaskPlugins.set(new CommandTask(plugin));
        }
    }

    /**
     * Remove the thread from {@link TaskManager} and finished Bukkit atached
     * threads. It <b>must</b> be call at the end of the thread that calls
     * {@link #createTask(JavaPlugin)}. If you not calling it the bukkit task
     * queue may blocks and crashs the server.
     * 
     * @throws TaskManagerException
     *             Is thown when current thread was not created with
     *             {@link #createTask(JavaPlugin)}.
     * @since 0.1.4
     */
    public static void removeTask() throws TaskManagerException {
        asyncTask();

        commandTaskPlugins.remove();
    }
}
