package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.cubenation.plugins.utils.PluginUtils;
import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.commandapi.annotation.Asynchron;
import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissionsNot;
import de.cubenation.plugins.utils.commandapi.annotation.NeededPlugin;
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.annotation.SenderPlayer;
import de.cubenation.plugins.utils.commandapi.annotation.SenderRemoteConsole;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandExecutionException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.exception.NoPermissionException;
import de.cubenation.plugins.utils.exceptionapi.DefaultMessageableException;
import de.cubenation.plugins.utils.exceptionapi.MessageableException;
import de.cubenation.plugins.utils.permissionapi.PermissionInterface;
import de.cubenation.plugins.utils.pluginapi.BasePlugin;
import de.cubenation.plugins.utils.wrapperapi.WrapperManager;

public class ChatCommand {
    // annotation data
    private ArrayList<String> mainNames = new ArrayList<String>();
    private ArrayList<String> subNames = new ArrayList<String>();
    private ArrayList<String> permissions = new ArrayList<String>();
    private ArrayList<String> permissionsNot = new ArrayList<String>();
    private ArrayList<String> neededPlugins = new ArrayList<String>();
    private ArrayList<String> worlds = new ArrayList<String>();
    private boolean isConsoleSender = false;
    private boolean isBlockSender = false;
    private boolean isPlayerSender = true;
    private boolean isRemoteConsoleSender = false;
    private boolean isMultipleSender = false;
    private int min = 0;
    private int max = -1;
    private String usage = "";
    private String help = "";
    private boolean runAsynchron = false;
    private Plugin plugin = null;

    // reflection objects
    private Object instance = null;
    private Method method = null;
    private PermissionInterface permissionInterface = null;
    private ErrorHandler errorHandler = null;
    private ChatService chatService;

    public ChatCommand(Object instance, Method method, ChatService chatService) throws CommandWarmUpException {
        this.instance = instance;
        this.method = method;

        Command annotation = method.getAnnotation(Command.class);

        for (String main : annotation.main()) {
            if (main == null || main.isEmpty()) {
                throw new CommandWarmUpException(instance.getClass(), "main attribute could not be empty");
            }

            if (main.contains(" ")) {
                throw new CommandWarmUpException(instance.getClass(), "main attribute could contain spaces");
            }

            String lowerMainCommand = main.toLowerCase();
            if (!mainNames.contains(lowerMainCommand)) {
                mainNames.add(lowerMainCommand);
            }
        }

        for (String sub : annotation.sub()) {
            if (sub == null || sub.isEmpty()) {
                continue;
            }

            if (sub.contains(" ")) {
                throw new CommandWarmUpException(instance.getClass(), "sub attribute could contain spaces");
            }

            String lowerSubCommand = sub.toLowerCase();
            if (!subNames.contains(lowerSubCommand)) {
                subNames.add(lowerSubCommand);
            }
        }

        min = annotation.min();
        max = annotation.max();

        usage = annotation.usage();
        help = annotation.help();

        short annoCount = 0;
        isConsoleSender = method.isAnnotationPresent(SenderConsole.class);
        if (isConsoleSender) {
            annoCount++;
        }

        isBlockSender = method.isAnnotationPresent(SenderBlock.class);
        if (isBlockSender) {
            annoCount++;
        }

        isRemoteConsoleSender = method.isAnnotationPresent(SenderRemoteConsole.class);
        if (isRemoteConsoleSender) {
            annoCount++;
        }

        if (annoCount > 0) {
            isPlayerSender = method.isAnnotationPresent(SenderPlayer.class);
            if (isPlayerSender) {
                annoCount++;
            }
        }

        if (annoCount > 1) {
            isMultipleSender = true;
        }

        boolean checkPermissionAnnotation = method.isAnnotationPresent(CommandPermissions.class);
        if (checkPermissionAnnotation) {
            CommandPermissions checkPermission = method.getAnnotation(CommandPermissions.class);

            for (String permission : checkPermission.value()) {
                if (permission == null || permission.isEmpty()) {
                    continue;
                }
                if (!permissions.contains(permission)) {
                    permissions.add(permission);
                }
            }
        }

        boolean checkPermissionNotAnnotation = method.isAnnotationPresent(CommandPermissionsNot.class);
        if (checkPermissionNotAnnotation) {
            CommandPermissionsNot checkPermissionNot = method.getAnnotation(CommandPermissionsNot.class);

            for (String permissionNot : checkPermissionNot.value()) {
                if (permissionNot == null || permissionNot.isEmpty()) {
                    continue;
                }
                if (!permissionsNot.contains(permissionNot)) {
                    permissionsNot.add(permissionNot);
                }
            }
        }

        boolean checkWorldAnnotation = method.isAnnotationPresent(World.class);
        if (checkWorldAnnotation) {
            World checkWorld = method.getAnnotation(World.class);

            for (String world : checkWorld.value()) {
                if (world == null || world.isEmpty()) {
                    continue;
                }
                String lowerWorld = world.toLowerCase();
                if (!worlds.contains(lowerWorld)) {
                    worlds.add(lowerWorld);
                }
            }
        }

        runAsynchron = method.isAnnotationPresent(Asynchron.class);

        boolean checkPluginNeededAnnotation = method.isAnnotationPresent(NeededPlugin.class);
        if (checkPluginNeededAnnotation) {
            NeededPlugin checkPluginNeeded = method.getAnnotation(NeededPlugin.class);

            for (String pluginNeeded : checkPluginNeeded.value()) {
                if (pluginNeeded == null || pluginNeeded.isEmpty()) {
                    continue;
                }
                if (!neededPlugins.contains(pluginNeeded)) {
                    neededPlugins.add(pluginNeeded);
                }
            }
        }

        this.chatService = chatService;
    }

    public boolean isCommandWithoutMinMaxWithoutWorld(CommandSender sender, String mainName, String subName) {
        boolean senderArg = false || (sender instanceof Player && isPlayerSender);
        senderArg = senderArg || (sender instanceof ConsoleCommandSender && isConsoleSender);
        senderArg = senderArg || (sender instanceof BlockCommandSender && isBlockSender);
        senderArg = senderArg || (sender instanceof RemoteConsoleCommandSender && isRemoteConsoleSender);

        boolean mainArg = mainNames.contains(mainName.toLowerCase());
        boolean subArg = ((subName.isEmpty() && subNames.isEmpty()) || (!subName.isEmpty() && subNames.contains(subName.toLowerCase())));
        return senderArg && mainArg && subArg;
    }

    public boolean isCommandWithoutWorlds(CommandSender sender, String mainName, String subName, int argSize) {
        boolean senderArg = false || (sender instanceof Player && isPlayerSender);
        senderArg = senderArg || (sender instanceof ConsoleCommandSender && isConsoleSender);
        senderArg = senderArg || (sender instanceof BlockCommandSender && isBlockSender);
        senderArg = senderArg || (sender instanceof RemoteConsoleCommandSender && isRemoteConsoleSender);

        boolean mainArg = mainNames.contains(mainName.toLowerCase());
        boolean subArg = ((subName.isEmpty() && subNames.isEmpty()) || (!subName.isEmpty() && subNames.contains(subName.toLowerCase())));
        boolean minMax = (argSize >= min && (argSize <= max || max == -1));
        return senderArg && mainArg && subArg && minMax;
    }

    public boolean isCommandWithoutPermission(CommandSender sender, String mainName, String subName, int argSize) {
        boolean senderArg = false || (sender instanceof Player && isPlayerSender && (worlds.isEmpty() || worlds.contains(((Player) sender).getWorld().getName()
                .toLowerCase())));
        senderArg = senderArg || (sender instanceof ConsoleCommandSender && isConsoleSender);
        senderArg = senderArg
                || (sender instanceof BlockCommandSender && isBlockSender && (worlds.isEmpty() || worlds.contains(((BlockCommandSender) sender).getBlock()
                        .getWorld().getName().toLowerCase())));
        senderArg = senderArg || (sender instanceof RemoteConsoleCommandSender && isRemoteConsoleSender);

        boolean mainArg = mainNames.contains(mainName.toLowerCase());
        boolean subArg = ((subName.isEmpty() && subNames.isEmpty()) || (!subName.isEmpty() && subNames.contains(subName.toLowerCase())));
        boolean minMax = (argSize >= min && (argSize <= max || max == -1));

        return senderArg && mainArg && subArg && minMax;
    }

    public boolean isExactCommand(CommandSender sender, String mainName, String subName, int argSize) {
        boolean senderArg = false || (sender instanceof Player && isPlayerSender && (worlds.isEmpty() || (((Player) sender).getWorld() != null && worlds
                .contains(((Player) sender).getWorld().getName().toLowerCase()))));
        senderArg = senderArg || (sender instanceof ConsoleCommandSender && isConsoleSender);
        senderArg = senderArg
                || (sender instanceof BlockCommandSender && isBlockSender && (worlds.isEmpty() || worlds.contains(((BlockCommandSender) sender).getBlock()
                        .getWorld().getName().toLowerCase())));
        senderArg = senderArg || (sender instanceof RemoteConsoleCommandSender && isRemoteConsoleSender);

        boolean mainArg = mainNames.contains(mainName.toLowerCase());
        boolean subArg = ((subName.isEmpty() && subNames.isEmpty()) || (!subName.isEmpty() && subNames.contains(subName.toLowerCase())));
        boolean minMax = (argSize >= min && (argSize <= max || max == -1));

        boolean permission = true;
        if (sender instanceof Player) {
            for (String permissionStr : permissions) {
                if (!hasPlayerRight((Player) sender, permissionStr)) {
                    permission = false;
                    break;
                }
            }
            if (permission) {
                for (String permissionNotStr : permissionsNot) {
                    if (hasPlayerRight((Player) sender, permissionNotStr)) {
                        permission = false;
                        break;
                    }
                }
            }
        }

        return senderArg && mainArg && subArg && minMax && permission;
    }

    private boolean checkCommand(CommandSender sender, String[] args) throws CommandException {
        if (isPlayerSender && sender instanceof Player) {
            return checkCommandForPlayer((Player) sender, args);
        } else {
            return checkCommandForOther(sender, args);
        }
    }

    private boolean checkCommandForPlayer(Player sender, String[] args) {
        if (!neededPlugins.isEmpty()) {
            for (String neededPlugin : neededPlugins) {
                if (WrapperManager.isPluginEnabled(neededPlugin)) {
                    chatService.one(sender, "player.neededPluginMissing");

                    if (plugin != null) {
                        Logger logger = plugin.getLogger();
                        if (logger != null) {
                            logger.log(Level.SEVERE, "missing plugin " + neededPlugin);
                        }
                    }

                    return false;
                }
            }
        }

        if (!permissions.isEmpty()) {
            for (String permission : permissions) {
                if (!hasPlayerRight(sender, permission)) {
                    chatService.one(sender, "all.noPermission");
                    return false;
                }
            }
        }

        if (!permissionsNot.isEmpty()) {
            for (String permissionNot : permissionsNot) {
                if (hasPlayerRight(sender, permissionNot)) {
                    chatService.one(sender, "all.noPermission");
                    return false;
                }
            }
        }

        if (min > 0 && min > args.length) {
            chatService.one(sender, "player.parameterMissing");
            if (!usage.isEmpty()) {
                chatService.one(sender, "player.commandUsage", mainNames.get(0), (!subNames.isEmpty() ? subNames.get(0) + " " : ""), usage);
            }
            return false;
        }

        if (max == 0 && args.length > 0) {
            chatService.one(sender, "player.parameterNo");
            if (!usage.isEmpty()) {
                chatService.one(sender, "player.commandUsage", mainNames.get(0), (!subNames.isEmpty() ? subNames.get(0) + " " : ""), usage);
            }
            return false;
        } else if (max > 0 && args.length > max) {
            chatService.one(sender, "player.parameterToMany");
            if (!usage.isEmpty()) {
                chatService.one(sender, "player.commandUsage", mainNames.get(0), (!subNames.isEmpty() ? subNames.get(0) + " " : ""), usage);
            }
            return false;
        }

        if (!worlds.isEmpty()) {
            String playerCurrentWorld = sender.getWorld().getName().toLowerCase();

            if (!worlds.contains(playerCurrentWorld)) {
                StringBuilder worldString = new StringBuilder("");
                for (String world : worlds) {
                    if (worldString.length() > 0) {
                        worldString.append(", ");
                    }
                    worldString.append(world);
                }
                chatService.one(sender, "player.wrongWorld", worldString.toString());
                return false;
            }
        }

        return true;
    }

    private boolean checkCommandForOther(CommandSender sender, String[] args) {
        if (!neededPlugins.isEmpty()) {
            for (String neededPlugin : neededPlugins) {
                if (WrapperManager.isPluginEnabled(neededPlugin)) {
                    chatService.one(sender, "other.neededPluginMissing");

                    if (plugin != null) {
                        Logger logger = plugin.getLogger();
                        if (logger != null) {
                            logger.log(Level.SEVERE, "missing plugin " + neededPlugin);
                        }
                    }

                    return false;
                }
            }
        }

        if (min > 0 && min > args.length) {
            chatService.one(sender, "other.parameterMissing");
            if (!usage.isEmpty()) {
                chatService.one(sender, "other.commandUsage", mainNames.get(0), (!subNames.isEmpty() ? subNames.get(0) + " " : ""), usage);
                return false;
            }
        }

        if (max == 0 && args.length > 0) {
            chatService.one(sender, "other.parameterNo");
            if (!usage.isEmpty()) {
                chatService.one(sender, "other.commandUsage", mainNames.get(0), (!subNames.isEmpty() ? subNames.get(0) + " " : ""), usage);
                return false;
            }
        } else if (max > 0 && args.length > max) {
            chatService.one(sender, "other.parameterToMany");
            if (!usage.isEmpty()) {
                chatService.one(sender, "other.commandUsage", mainNames.get(0), (!subNames.isEmpty() ? subNames.get(0) + " " : ""), usage);
                return false;
            }
        }

        if (sender instanceof BlockCommandSender) {
            if (!worlds.isEmpty()) {
                String blockCurrentWorld = ((BlockCommandSender) sender).getBlock().getWorld().getName().toLowerCase();

                if (!worlds.contains(blockCurrentWorld)) {
                    StringBuilder worldString = new StringBuilder("");
                    for (String world : worlds) {
                        if (worldString.length() > 0) {
                            worldString.append(", ");
                        }
                        worldString.append(world);
                    }
                    chatService.one(sender, "block.wrongWorld", worldString.toString());
                    return false;
                }
            }
        }

        return true;
    }

    public void execute(final CommandSender sender, final String[] args) throws CommandException {
        if (!checkCommand(sender, args)) {
            return;
        }

        final ArrayList<Object> arguments = getParameterList(sender, args);
        if (plugin != null) {
            Thread task = new Thread("CommandRunner-" + method.getName()) {
                @Override
                public void run() {
                    TaskManager.createTask(plugin);
                    if (!runAsynchron) {
                        TaskManager.syncTask();
                    }

                    executeExact(sender, args, arguments);

                    TaskManager.removeTask();
                }

            };

            Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
        } else {
            try {
                method.invoke(instance, arguments.toArray());
            } catch (Exception e) {
                if (e instanceof IllegalArgumentException) {
                    if (args.length == 1) {
                        try {
                            method.invoke(instance, arguments.get(0), args[0]);
                            return;
                        } catch (Exception e1) {
                            throw new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e1);
                        }
                    } else if (args.length == 0) {
                        try {
                            method.invoke(instance, arguments.get(0));
                            return;
                        } catch (Exception e1) {
                            throw new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e1);
                        }
                    }
                }

                throw new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e);
            }
        }
    }

    private void executeExact(CommandSender sender, String[] args, ArrayList<Object> arguments) {
        try {
            // player & string[]
            method.invoke(instance, arguments.toArray());
        } catch (InvocationTargetException e) {
            if (e.getCause() != null && e.getCause() instanceof MessageableException) {
                chatCustomError(sender, (MessageableException) e.getCause());
            } else {
                executeLike(sender, args, arguments, e);
            }
        } catch (Exception e) {
            executeLike(sender, args, arguments, e);
        }
    }

    private void chatCustomError(CommandSender sender, MessageableException e) {
        try {
            if (e instanceof DefaultMessageableException) {
                if (WrapperManager.isPluginEnabled(WrapperManager.PLUGIN_NAME_PLUGIN_UTILS)) {
                    PluginUtils plugin = (PluginUtils) Bukkit.getServer().getPluginManager().getPlugin(WrapperManager.PLUGIN_NAME_PLUGIN_UTILS);

                    ((BasePlugin) plugin).getChatService().oneText(sender, e.getLocaleMessage((BasePlugin) plugin));
                }
            } else if (plugin != null && plugin instanceof BasePlugin && ((BasePlugin) plugin).getChatService() != null) {
                ((BasePlugin) plugin).getChatService().oneText(sender, e.getLocaleMessage((BasePlugin) plugin));
            }
        } catch (Exception e1) {
        }
    }

    private void executeLike(CommandSender sender, String[] args, ArrayList<Object> arguments, Exception e) {
        boolean writeError = false;
        boolean isError = true;
        if (e instanceof IllegalArgumentException) {
            if (args.length == 1) {
                try {
                    // player & string
                    method.invoke(instance, arguments.get(0), args[0]);
                    isError = false;
                } catch (InvocationTargetException e1) {
                    if (e1.getCause() != null && e1.getCause() instanceof MessageableException) {
                        chatCustomError(sender, (MessageableException) e1.getCause());
                        isError = false;
                    } else {
                        writeError = true;
                        logError(e1);
                    }
                } catch (Exception e1) {
                    writeError = true;
                    logError(e1);
                }
            } else if (args.length == 0) {
                try {
                    // player
                    method.invoke(instance, arguments.get(0));
                    isError = false;
                } catch (InvocationTargetException e1) {
                    if (e1.getCause() != null && e1.getCause() instanceof MessageableException) {
                        chatCustomError(sender, (MessageableException) e1.getCause());
                        isError = false;
                    } else {
                        writeError = true;
                        logError(e1);
                    }
                } catch (Exception e1) {
                    writeError = true;
                    logError(e1);
                }
            }
        }

        if (isError && !writeError) {
            logError(e);
        }
    }

    private void logError(Exception e) {
        if (errorHandler != null) {
            errorHandler.onError(new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e));
        } else {
            if (plugin.getLogger() != null) {
                plugin.getLogger().log(Level.SEVERE, "error on execute " + method.getName(),
                        new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e));
            } else {
                new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e).printStackTrace();
            }
        }
    }

    private ArrayList<Object> getParameterList(CommandSender sender, String[] args) {
        ArrayList<Object> arguments = new ArrayList<Object>();
        if (isMultipleSender) {
            arguments.add(sender);
        } else {
            if (isConsoleSender) {
                arguments.add((ConsoleCommandSender) sender);
            } else if (isPlayerSender) {
                arguments.add((Player) sender);
            } else if (isBlockSender) {
                arguments.add((BlockCommandSender) sender);
            } else if (isRemoteConsoleSender) {
                arguments.add((RemoteConsoleCommandSender) sender);
            }
        }

        arguments.add(args);
        return arguments;
    }

    private boolean hasPlayerRight(Player player, String rightName) {
        boolean has = false;

        if (permissionInterface != null) {
            has = permissionInterface.hasPermission(player, rightName);
        } else {
            has = player.hasPermission(rightName);
        }
        return has;
    }

    public void sendHelp(String mainCommand, String subCommand, CommandSender sender) throws NoPermissionException {
        if (help.isEmpty()) {
            return;
        }

        if ((mainNames.contains(mainCommand.toLowerCase()) && subCommand.isEmpty())
                || (mainNames.contains(mainCommand.toLowerCase()) && subNames.contains(subCommand.toLowerCase()))) {

            for (String main : mainNames) {
                if (isConsoleSender || isBlockSender || isRemoteConsoleSender) {
                    chatService.one(sender, "all.helpMessage", buildHelpMessage(main, false));
                } else if (isPlayerSender) {
                    if (!permissions.isEmpty()) {
                        for (String permission : permissions) {
                            if (!hasPlayerRight((Player) sender, permission)) {
                                throw new NoPermissionException();
                            }
                        }
                    }
                    if (!permissionsNot.isEmpty()) {
                        for (String permissionNot : permissionsNot) {
                            if (hasPlayerRight((Player) sender, permissionNot)) {
                                throw new NoPermissionException();
                            }
                        }
                    }

                    chatService.one(sender, "all.helpMessage", buildHelpMessage(main, true));
                }
            }
        }
    }

    private String buildHelpMessage(String main, boolean isPlayer) {
        String helpMsg = "";
        if (isPlayer) {
            helpMsg += ChatColor.AQUA;
        }
        helpMsg += "/" + main;

        if (!subNames.isEmpty()) {
            helpMsg += " ";

            boolean first = true;
            for (String sub : subNames) {
                if (first) {
                    first = false;
                } else {
                    helpMsg += "/";
                }
                helpMsg += sub;
            }
        }

        if (!usage.isEmpty()) {
            helpMsg += " " + usage;
        }

        if (isPlayer) {
            helpMsg += ChatColor.WHITE;
        }
        helpMsg += " - " + help;

        return helpMsg;
    }

    public void setPermissionInterface(PermissionInterface permissionInterface) {
        this.permissionInterface = permissionInterface;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public Object getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

    public ArrayList<String> getMainAliases() {
        return mainNames;
    }

    public ArrayList<String> getSubAliases() {
        return subNames;
    }

    public ArrayList<String> getWorlds() {
        return worlds;
    }

    public int getMinAttribute() {
        return min;
    }

    public int getMaxAttribute() {
        return max;
    }

    public Class<? extends CommandSender> getSenderType() {
        if (isMultipleSender) {
            return CommandSender.class;
        } else if (isBlockSender) {
            return BlockCommandSender.class;
        } else if (isConsoleSender) {
            return ConsoleCommandSender.class;
        } else if (isRemoteConsoleSender) {
            return RemoteConsoleCommandSender.class;
        } else {
            return Player.class;
        }
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public boolean isAsynchronCommand() {
        return runAsynchron;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    public ArrayList<String> getPermissionsNot() {
        return permissionsNot;
    }

    public void setPermissionsNot(ArrayList<String> permissionsNot) {
        this.permissionsNot = permissionsNot;
    }

    public ArrayList<String> getNeededPlugins() {
        return neededPlugins;
    }

    public void setNeededPlugins(ArrayList<String> neededPlugins) {
        this.neededPlugins = neededPlugins;
    }

    @Override
    public String toString() {
        String type = "[NONE]";

        if (isConsoleSender) {
            type = "[CONSOLE]";
        }
        if (isBlockSender) {
            type = "[BLOCK]";
        }
        if (isPlayerSender) {
            type = "[PLAYER]";
        }
        if (isRemoteConsoleSender) {
            type = "[REMOTECONSOLE]";
        }
        if (isMultipleSender) {
            type = "[MULTI]";
        }

        String perm = "";
        if (!permissions.isEmpty()) {
            perm = " [permission: " + StringUtils.join(permissions, ", ") + "]";
        }
        if (!permissionsNot.isEmpty()) {
            perm = " [permissionNot: " + StringUtils.join(permissionsNot, ", ") + "]";
        }

        String world = "";
        if (!worlds.isEmpty()) {
            world = " [world: " + StringUtils.join(worlds, ", ") + "]";
        }

        String plugin = "";
        if (!neededPlugins.isEmpty()) {
            plugin = " [plugin dependency: " + StringUtils.join(neededPlugins, ", ") + "]";
        }

        return "/" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + type + perm + world + plugin;
    }
}
