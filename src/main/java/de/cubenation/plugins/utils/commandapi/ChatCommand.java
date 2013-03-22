package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.commandapi.annotation.Asynchron;
import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.annotation.SenderPlayer;
import de.cubenation.plugins.utils.commandapi.annotation.SenderRemoteConsole;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandExecutionException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.exception.NoPermissionException;
import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class ChatCommand {
    // annotation data
    private ArrayList<String> mainNames = new ArrayList<String>();
    private ArrayList<String> subNames = new ArrayList<String>();
    private ArrayList<String> permissions = new ArrayList<String>();
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
    private JavaPlugin plugin = null;

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

            String lowerMainCommand = main.toLowerCase();
            if (!mainNames.contains(lowerMainCommand)) {
                mainNames.add(lowerMainCommand);
            }
        }

        for (String sub : annotation.sub()) {
            if (sub == null || sub.isEmpty()) {
                continue;
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

    public boolean isExactCommand(CommandSender sender, String mainName, String subName, int argSize) {
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

    private boolean checkCommand(CommandSender sender, String[] args) throws CommandException {
        if (isPlayerSender && sender instanceof Player) {
            return checkCommandForPlayer((Player) sender, args);
        } else {
            return checkCommandForOther(sender, args);
        }
    }

    private boolean checkCommandForPlayer(Player sender, String[] args) {
        if (permissions.size() > 0) {
            for (String permission : permissions) {
                if (!hasPlayerRight(sender, permission)) {
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

        if (worlds.size() > 0) {
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
            if (worlds.size() > 0) {
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

    public void execute(CommandSender sender, final String[] args) throws CommandException {
        if (!checkCommand(sender, args)) {
            return;
        }

        final ArrayList<Object> arguments = getParameterList(sender, args);

        if (runAsynchron) {
            Thread task = new Thread("CommandRunner-" + method.getName()) {
                @Override
                public void run() {
                    try {
                        method.invoke(instance, arguments.toArray());
                    } catch (Exception e) {
                        if (e instanceof IllegalArgumentException) {
                            if (args.length == 1) {
                                try {
                                    method.invoke(instance, arguments.get(0), args[0]);
                                    return;
                                } catch (Exception e1) {
                                    errorHandler.onError(new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e1));
                                }
                            } else if (args.length == 0) {
                                try {
                                    method.invoke(instance, arguments.get(0));
                                    return;
                                } catch (Exception e1) {
                                    errorHandler.onError(new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e1));
                                }
                            }
                        }

                        errorHandler.onError(new CommandExecutionException(instance.getClass(), "error on execute " + method.getName(), e));
                    }
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
                    if (permissions.size() > 0) {
                        for (String permission : permissions) {
                            if (!hasPlayerRight((Player) sender, permission)) {
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

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public boolean isAsynchronCommand() {
        return runAsynchron;
    }
}
