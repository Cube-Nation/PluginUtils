package de.cubenation.plugins.utils.pluginapi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.PersistenceException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.avaje.ebean.EbeanServer;

import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.commandapi.CommandsManager;
import de.cubenation.plugins.utils.commandapi.ErrorHandler;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandManagerException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.permissionapi.PermissionService;

public abstract class BasePlugin extends JavaPlugin {
    // framework services
    protected PermissionService permissionService;
    protected ChatService chatService;
    protected CommandsManager commandsManager;

    private EbeanServer customDatabaseServer = null;
    private ErrorHandler errorHandler;

    @Override
    public final EbeanServer getDatabase() {
        if (customDatabaseServer != null) {
            return customDatabaseServer;
        } else {
            return super.getDatabase();
        }
    }

    @Override
    public final void onEnable() {
        preEnableAction();

        errorHandler = createCustomErrorHandler();
        if (errorHandler == null) {
            errorHandler = new ErrorHandler() {
                @Override
                public void onError(Throwable thrown) {
                    BasePlugin.this.onError(thrown);
                }
            };
        }

        saveDefaultConfig();
        reloadConfig();

        customDatabaseServer = getCustomDatabaseServer();
        if (customDatabaseServer == null) {
            setupDatabase();
        }
        migrateOldData();

        permissionService = new PermissionService();
        chatService = new ChatService(this, permissionService);

        initialCustomServices();

        startCustomServices();

        registerCustomEventListeners();

        try {
            commandsManager = new CommandsManager(this);
            commandsManager.setPermissionInterface(permissionService);
            commandsManager.setErrorHandler(errorHandler);
            addCommands();
        } catch (CommandWarmUpException e) {
            getLogger().log(Level.SEVERE, "error on register command", e);
        } catch (CommandManagerException e) {
            getLogger().log(Level.SEVERE, "error on inital command manager", e);
        }

        getLogger().info("version " + getDescription().getVersion() + " enabled");

        startCustomServices();

        startScheduleTasks();

        postEnableAction();
    }

    private void startScheduleTasks() {
        List<ScheduleTask> scheduledTasks = new ArrayList<ScheduleTask>();

        registerScheduledTasks(scheduledTasks);

        for (ScheduleTask scheduledTask : scheduledTasks) {
            if (scheduledTask.getRepeat() != null) {
                getServer().getScheduler().scheduleSyncRepeatingTask(this, scheduledTask.getTask(), scheduledTask.getStart(), scheduledTask.getRepeat());
            } else {
                getServer().getScheduler().scheduleSyncDelayedTask(this, scheduledTask.getTask(), scheduledTask.getStart());
            }
        }
    }

    private void registerCustomEventListeners() {
        List<Listener> customEvents = new ArrayList<Listener>();
        registerCustomEventListeners(customEvents);

        for (Listener customEvent : customEvents) {
            getServer().getPluginManager().registerEvents(customEvent, this);
        }
    }

    protected void migrateOldData() {
    }

    private void setupDatabase() {
        try {
            List<Class<?>> databaseClasses = getDatabaseClasses();

            for (Class<?> databaseClass : databaseClasses) {
                getDatabase().find(databaseClass).findRowCount();
            }
        } catch (PersistenceException ex) {
            getLogger().info("Installing database due to first time usage");
            installDDL();
        }
    }

    @Override
    public final List<Class<?>> getDatabaseClasses() {
        List<Class<?>> databaseModel = super.getDatabaseClasses();

        registerDatabaseModel(databaseModel);

        return databaseModel;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            commandsManager.execute(sender, command, label, args);
        } catch (CommandException e) {
            getLogger().log(Level.SEVERE, "error on command", e);
            return false;
        }
        return true;
    }

    @Override
    public final List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return commandsManager.getTabCompleteList(sender, command, alias, args);
    }

    @Override
    public final void onDisable() {
        commandsManager.clear();

        stopCustomServices();

        saveConfig();

        getLogger().info("unloaded");
    }

    public final void onError(Throwable thrown) {
        getLogger().log(Level.SEVERE, "error on plugin", thrown);
    }

    private void addCommands() throws CommandWarmUpException {
        List<CommandSet> commandSets = new ArrayList<CommandSet>();

        registerCommands(commandSets);

        for (CommandSet commandSet : commandSets) {
            commandsManager.add(commandSet.getClazz(), commandSet.getParameters());
        }
    }

    protected ErrorHandler createCustomErrorHandler() {
        return null;
    }

    protected void preEnableAction() {
    }

    protected void postEnableAction() {
    }

    protected void initialCustomServices() {
    }

    protected void startCustomServices() {
    }

    protected void stopCustomServices() {
    }

    protected void registerCommands(List<CommandSet> list) {
    }

    protected void registerCustomEventListeners(List<Listener> list) {
    }

    protected void registerDatabaseModel(List<Class<?>> list) {
    }

    protected EbeanServer getCustomDatabaseServer() {
        return null;
    }

    protected void registerScheduledTasks(List<ScheduleTask> list) {
    }
}
