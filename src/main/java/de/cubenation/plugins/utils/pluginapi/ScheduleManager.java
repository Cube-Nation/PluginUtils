package de.cubenation.plugins.utils.pluginapi;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

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
     *            the reference to the plugin scheduling task
     * @throws IllegalArgumentException
     *             if plugin is null
     * 
     * @since 0.1.4
     */
    public static void addPlugin(Plugin plugin) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null");

        registeredPlugins.put(plugin.getClass().getName(), plugin);
    }

    /**
     * Removes plugin object. Do this on plugin disabling.
     * 
     * @param plugin
     *            the reference to the plugin scheduling task
     * @throws IllegalArgumentException
     *             if plugin is null
     * 
     * @since 0.1.4
     */
    public static void removePlugin(Plugin plugin) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null");

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
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static int scheduleSyncDelayedTask(Runnable task, long delay) throws IllegalStateException {
        return Bukkit.getScheduler().scheduleSyncDelayedTask(getCurrentPlugin(), task, delay);
    }

    /**
     * Schedules a once off task to occur as soon as possible.<br>
     * <br>
     * This task will be executed by the main server thread.
     * 
     * @param task
     *            Task to be executed
     * @return Task id number (-1 if scheduling failed)
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static int scheduleSyncDelayedTask(Runnable task) throws IllegalStateException {
        return Bukkit.getScheduler().scheduleSyncDelayedTask(getCurrentPlugin(), task);
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
     *             if task is null
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskAsynchronously(Runnable task) throws IllegalArgumentException, IllegalStateException {
        return Bukkit.getScheduler().runTaskAsynchronously(getCurrentPlugin(), task);
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
     *             if task is null
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskLater(Runnable task, long delay) throws IllegalArgumentException, IllegalStateException {
        return Bukkit.getScheduler().runTaskLater(getCurrentPlugin(), task, delay);
    }

    /**
     * Returns a task that will run on the next server tick.
     * 
     * @param task
     *            the task to be run
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException
     *             if task is null
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTask(Runnable task) throws IllegalArgumentException, IllegalStateException {
        return Bukkit.getScheduler().runTask(getCurrentPlugin(), task);
    }

    /**
     * Schedules a repeating task.
     * <p>
     * This task will be executed by the main server thread.
     * 
     * @param task
     *            Task to be executed
     * @param delay
     *            Delay in server ticks before executing first repeat
     * @param period
     *            Period in server ticks of the task
     * @return Task id number (-1 if scheduling failed)
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static int scheduleSyncRepeatingTask(Runnable task, long delay, long period) throws IllegalStateException {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(getCurrentPlugin(), task, delay, period);
    }

    /**
     * Calls a method on the main thread and returns a Future object. This task
     * will be executed by the main server thread.
     * <ul>
     * <li>Note: The Future.get() methods must NOT be called from the main
     * thread.
     * <li>Note2: There is at least an average of 10ms latency until the
     * isDone() method returns true.
     * </ul>
     * 
     * @param <T>
     *            The callable's return type
     * @param task
     *            Task to be executed
     * @return Future Future object related to the task
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static <T> Future<T> callSyncMethod(Callable<T> task) throws IllegalStateException {
        return Bukkit.getScheduler().callSyncMethod(getCurrentPlugin(), task);
    }

    /**
     * Removes task from scheduler.
     * 
     * @param taskId
     *            Id number of task to be removed
     * 
     * @since 0.1.4
     */
    public static void cancelTask(int taskId) {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    /**
     * Removes all tasks associated with a particular plugin from the scheduler.
     * 
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static void cancelTasks() throws IllegalStateException {
        Bukkit.getScheduler().cancelTasks(getCurrentPlugin());
    }

    /**
     * Removes all tasks from the scheduler.
     * 
     * @since 0.1.4
     */
    public static void cancelAllTasks() {
        Bukkit.getScheduler().cancelAllTasks();
    }

    /**
     * Check if the task currently running.
     * <p>
     * A repeating task might not be running currently, but will be running in
     * the future. A task that has finished, and does not repeat, will not be
     * running ever again.
     * <p>
     * Explicitly, a task is running if there exists a thread for it, and that
     * thread is alive.
     * 
     * @param taskId
     *            The task to check.
     *            <p>
     * @return If the task is currently running.
     * 
     * @since 0.1.4
     */
    public static boolean isCurrentlyRunning(int taskId) {
        return Bukkit.getScheduler().isCurrentlyRunning(taskId);
    }

    /**
     * Check if the task queued to be run later.
     * <p>
     * If a repeating task is currently running, it might not be queued now but
     * could be in the future. A task that is not queued, and not running, will
     * not be queued again.
     * 
     * @param taskId
     *            The task to check.
     *            <p>
     * @return If the task is queued to be run.
     * 
     * @since 0.1.4
     */
    public static boolean isQueued(int taskId) {
        return Bukkit.getScheduler().isQueued(taskId);
    }

    /**
     * Returns a list of all active workers.
     * <p>
     * This list contains asynch tasks that are being executed by separate
     * threads.
     * 
     * @return Active workers
     * 
     * @since 0.1.4
     */
    public static List<BukkitWorker> getActiveWorkers() {
        return Bukkit.getScheduler().getActiveWorkers();
    }

    /**
     * Returns a list of all pending tasks. The ordering of the tasks is not
     * related to their order of execution.
     * 
     * @return Active workers
     * 
     * @since 0.1.4
     */
    public static List<BukkitTask> getPendingTasks() {
        return Bukkit.getScheduler().getPendingTasks();
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will run asynchronously after the specified number of
     * server ticks.
     * 
     * @param task
     *            the task to be run
     * @param delay
     *            the ticks to wait before running the task
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException
     *             if task is null
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskLaterAsynchronously(Runnable task, long delay) throws IllegalArgumentException, IllegalStateException {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(getCurrentPlugin(), task, delay);
    }

    /**
     * Returns a task that will repeatedly run until cancelled, starting after
     * the specified number of server ticks.
     * 
     * @param task
     *            the task to be run
     * @param delay
     *            the ticks to wait before running the task
     * @param period
     *            the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException
     *             if task is null
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskTimer(Runnable task, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        return Bukkit.getScheduler().runTaskTimer(getCurrentPlugin(), task, delay, period);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Returns a task that will repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     * 
     * @param task
     *            the task to be run
     * @param delay
     *            the ticks to wait before running the task for the first time
     * @param period
     *            the ticks to wait between runs
     * @return a BukkitTask that contains the id number
     * @throws IllegalArgumentException
     *             if task is null
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static BukkitTask runTaskTimerAsynchronously(Runnable task, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(getCurrentPlugin(), task, delay, period);
    }

    /**
     * Search for the current bukkit plugin.
     * 
     * @return current bukkit plugin
     * @throws IllegalStateException
     *             if plugin not added before
     * 
     * @since 0.1.4
     */
    public static Plugin getCurrentPlugin() throws IllegalStateException {
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

        throw new IllegalStateException("Plugin cannot be found");
    }
}
