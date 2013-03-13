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
import de.cubenation.plugins.utils.commandapi.pluginwrapper.PermissionsExWrapper;

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

    // reflection objects
    private Object instance = null;
    private Method method = null;
    private PermissionInterface permissionInterface = null;

    public ChatCommand(Object instance, Method method) throws CommandWarmUpException {
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
    }

    public boolean isCommand(CommandSender sender, String mainName, String subName) {
        return ((sender instanceof Player && isPlayerSender) || (sender instanceof ConsoleCommandSender && isConsoleSender)
                || (sender instanceof BlockCommandSender && isBlockSender) || (sender instanceof RemoteConsoleCommandSender && isRemoteConsoleSender))
                && mainNames.contains(mainName.toLowerCase()) && subNames.contains(subName.toLowerCase());
    }

    public boolean isCommand(CommandSender sender, String mainName) {
        return ((sender instanceof Player && isPlayerSender) || (sender instanceof ConsoleCommandSender && isConsoleSender)
                || (sender instanceof BlockCommandSender && isBlockSender) || (sender instanceof RemoteConsoleCommandSender && isRemoteConsoleSender))
                && mainNames.contains(mainName.toLowerCase()) && subNames.isEmpty();
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
                    sender.sendMessage(ChatColor.RED + "Nicht ausreichende Berechtigungen");
                    return false;
                }
            }
        }

        if (min > 0 && min > args.length) {
            sender.sendMessage(ChatColor.RED + "Mindest Anzahl an Parameter nicht angegeben");
            if (!usage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + usage);
            }
            return false;
        }

        if (max == 0 && args.length > 0) {
            sender.sendMessage(ChatColor.RED + "Befehl unterstützt keine Parameter");
            if (!usage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + usage);
            }
            return false;
        } else if (max > 0 && args.length > max) {
            sender.sendMessage(ChatColor.RED + "Zu viel Parameter angegeben");
            if (!usage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + usage);
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
                sender.sendMessage(ChatColor.RED + "Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in " + worldString.toString()
                        + " verwendet werden.");
                return false;
            }
        }

        return true;
    }

    private boolean checkCommandForOther(CommandSender sender, String[] args) {
        if (min > 0 && min > args.length) {
            sender.sendMessage("Mindest Anzahl an Parameter nicht angegeben");
            if (!usage.isEmpty()) {
                sender.sendMessage("Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + usage);
                return false;
            }
        }

        if (max == 0 && args.length > 0) {
            sender.sendMessage("Befehl unterstützt keine Parameter");
            if (!usage.isEmpty()) {
                sender.sendMessage("Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + usage);
                return false;
            }
        } else if (max > 0 && args.length > max) {
            sender.sendMessage("Zu viel Parameter angegeben");
            if (!usage.isEmpty()) {
                sender.sendMessage("Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " " + usage);
                return false;
            }
        }

        return true;
    }

    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!checkCommand(sender, args)) {
            return;
        }

        ArrayList<Object> arguments = getParameterList(sender, args);

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
        } else if (Bukkit.getServer() != null && Bukkit.getServer().getPluginManager() != null
                && Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
            has = PermissionsExWrapper.hasPlayerRight(player, rightName);
        } else {
            has = player.hasPermission(rightName);
        }
        return has;
    }

    public void sendHelp(String mainCommand, String subCommand, CommandSender sender) {
        if (help.isEmpty()) {
            return;
        }

        if ((mainNames.contains(mainCommand.toLowerCase()) && subCommand.isEmpty())
                || (mainNames.contains(mainCommand.toLowerCase()) && subNames.contains(subCommand.toLowerCase()))) {
            for (String main : mainNames) {
                if (isConsoleSender || isBlockSender || isRemoteConsoleSender) {
                    sender.sendMessage(buildHelpMessage(main, false));
                } else if (isPlayerSender) {
                    if (permissions.size() > 0) {
                        for (String permission : permissions) {
                            if (!hasPlayerRight((Player) sender, permission)) {
                                return;
                            }
                        }
                    }

                    ((Player) sender).sendMessage(buildHelpMessage(main, true));
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
}
