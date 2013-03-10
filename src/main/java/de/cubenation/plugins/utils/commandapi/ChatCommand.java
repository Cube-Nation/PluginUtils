package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ChatCommand {
    // annotation data
    private ArrayList<String> mainNames = new ArrayList<String>();
    private ArrayList<String> subNames = new ArrayList<String>();
    private ArrayList<String> permissions = new ArrayList<String>();
    private ArrayList<String> worlds = new ArrayList<String>();
    private boolean consoleCommand = false;
    private int min = 0;
    private int max = -1;
    private String usage = "";
    private String help = "";

    // reflection objects
    private Object instance;
    private Method method;

    public ChatCommand(Object instance, Method method) {
        this.instance = instance;
        this.method = method;

        Command annotation = method.getAnnotation(Command.class);

        for (String main : annotation.main()) {
            mainNames.add(main.toLowerCase());
        }

        for (String sub : annotation.sub()) {
            subNames.add(sub.toLowerCase());
        }

        min = annotation.min();
        max = annotation.max();
        usage = annotation.usage();
        help = annotation.help();

        consoleCommand = method.isAnnotationPresent(Console.class);

        boolean checkPermissionAnnotation = method.isAnnotationPresent(CommandPermissions.class);
        if (checkPermissionAnnotation) {
            CommandPermissions checkPermission = method.getAnnotation(CommandPermissions.class);
            permissions.addAll(Arrays.asList(checkPermission.value()));
        }

        boolean checkWorldAnnotation = method.isAnnotationPresent(World.class);
        if (checkWorldAnnotation) {
            World checkWorld = method.getAnnotation(World.class);

            for (String world : checkWorld.value()) {
                worlds.add(world.toLowerCase());
            }
        }
    }

    public boolean isCommand(String mainName, String subName) {
        return mainNames.contains(mainName.toLowerCase()) && subNames.contains(subName.toLowerCase());
    }

    public boolean isCommand(String mainName) {
        return mainNames.contains(mainName.toLowerCase()) && subNames.isEmpty();
    }

    public void execute(CommandSender sender, String[] args) throws CommandException {
        ArrayList<Object> arguments = new ArrayList<Object>();
        if (consoleCommand) {
            arguments.add((ConsoleCommandSender) sender);

            if (min > 0 && min > args.length) {
                ((ConsoleCommandSender) sender).sendMessage("Mindest Anzahl an Parameter nicht ausreichend");
                if (!usage.isEmpty()) {
                    ((ConsoleCommandSender) sender).sendMessage("Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + " " + usage);
                    return;
                }
            }

            if (max == 0 && args.length > 0) {
                ((ConsoleCommandSender) sender).sendMessage("Befehl unterstützt keine Parameter");
                if (!usage.isEmpty()) {
                    ((ConsoleCommandSender) sender).sendMessage("Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + " " + usage);
                    return;
                }
            } else if (max > 0 && args.length > max) {
                ((ConsoleCommandSender) sender).sendMessage("Zu viel Parameter angegeben");
                if (!usage.isEmpty()) {
                    ((ConsoleCommandSender) sender).sendMessage("Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + " " + usage);
                    return;
                }
            }
        } else {
            if (permissions.size() > 0) {
                for (String permission : permissions) {
                    if (!hasPlayerRight((Player) sender, permission)) {
                        ((Player) sender).sendMessage(ChatColor.RED + "Nicht ausreichende Berechtigungen");
                        return;
                    }
                }
            }

            if (min > 0 && min > args.length) {
                ((Player) sender).sendMessage(ChatColor.RED + "Mindest Anzahl an Parameter nicht ausreichend");
                if (!usage.isEmpty()) {
                    ((Player) sender).sendMessage(ChatColor.RED + "Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + " " + usage);
                }
                return;
            }

            if (max == 0 && args.length > 0) {
                ((Player) sender).sendMessage(ChatColor.RED + "Befehl unterstützt keine Parameter");
                if (!usage.isEmpty()) {
                    ((Player) sender).sendMessage(ChatColor.RED + "Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + " " + usage);
                }
                return;
            } else if (max > 0 && args.length > max) {
                ((Player) sender).sendMessage(ChatColor.RED + "Zu viel Parameter angegeben");
                if (!usage.isEmpty()) {
                    ((Player) sender).sendMessage(ChatColor.RED + "Befehlssyntax: /" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + " " + usage);
                }
                return;
            }

            if (worlds.size() > 0) {
                String playerCurrentWorld = ((Player) sender).getWorld().getName().toLowerCase();

                if (!worlds.contains(playerCurrentWorld)) {
                    StringBuilder worldString = new StringBuilder("");
                    for (String world : worlds) {
                        if (worldString.length() > 0) {
                            worldString.append(", ");
                        }
                        worldString.append(world);
                    }
                    ((Player) sender).sendMessage(ChatColor.RED + "Du befindest dich nicht in der richtigen Spielwelt! Der Befehl kann nur in "
                            + worldString.toString() + " verwendet werden.");
                    return;
                }
            }

            arguments.add((Player) sender);
        }

        arguments.add(args);
        try {
            method.invoke(instance, arguments.toArray());
        } catch (Exception e) {
            throw new CommandException(e);
        }
    }

    public boolean hasPlayerRight(Player player, String rightName) {
        boolean has = false;

        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
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
            if (consoleCommand) {
                ((ConsoleCommandSender) sender).sendMessage("/" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "") + " - " + help);
            } else {
                if (permissions.size() > 0) {
                    for (String permission : permissions) {
                        if (!hasPlayerRight((Player) sender, permission)) {
                            return;
                        }
                    }
                    ((Player) sender).sendMessage(ChatColor.AQUA + "/" + mainNames.get(0) + (!subNames.isEmpty() ? " " + subNames.get(0) : "")
                            + ChatColor.WHITE + " - " + help);
                }
            }
        }
    }
}
