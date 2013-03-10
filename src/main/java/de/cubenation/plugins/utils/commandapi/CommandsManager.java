package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandsManager {
    private final static Logger log = Logger.getLogger("Minecraft.CommandsManager");
    private JavaPlugin plugin;
    private ArrayList<ChatCommand> commands = new ArrayList<ChatCommand>();

    public CommandsManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void add(Class<?> commandClass) {
        try {
            Constructor<?> ctor = commandClass.getConstructor(JavaPlugin.class);
            Object instance = ctor.newInstance(plugin);
            Method[] declaredMethods = instance.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                boolean annotationPresent = declaredMethod.isAnnotationPresent(Command.class);
                if (annotationPresent) {
                    commands.add(new ChatCommand(instance, declaredMethod));
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "[" + plugin.getClass().getSimpleName() + "] error on command warmup", e);
        }
    }

    public void clear() {
        commands.clear();
    }

    public void execute(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) throws CommandException {
        findAndExecuteCommand(sender, commandLabel, args);
    }

    private void findAndExecuteCommand(CommandSender sender, String commandLabel, String[] args) throws CommandException {
        String mainCommand = commandLabel;
        String subCommand = "";
        boolean helpCommand = false;

        if (mainCommand.isEmpty()) {
            return;
        }

        if (args.length > 0) {
            subCommand = args[0];
            if (args.length > 1 && (args[1].equalsIgnoreCase("help") || args[1].equals("?"))) {
                helpCommand = true;
            }
        }

        if (!subCommand.isEmpty() && (subCommand.equalsIgnoreCase("help") || subCommand.equals("?"))) {
            subCommand = "";
            helpCommand = true;
        }

        if (helpCommand) {
            // search for defined help command
            for (ChatCommand command : commands) {
                if (command.isCommand(mainCommand, "help") || command.isCommand(mainCommand, "?")) {
                    Queue<String> argsQueue = new LinkedList<String>(Arrays.asList(args));

                    command.execute(sender, argsQueue.toArray(new String[] {}));

                    return;
                }
            }

            for (ChatCommand command : commands) {
                command.sendHelp(mainCommand, subCommand, sender);
            }

            return;
        }

        if (!subCommand.isEmpty()) {
            for (ChatCommand command : commands) {
                if (command.isCommand(mainCommand, subCommand)) {
                    Queue<String> argsQueue = new LinkedList<String>(Arrays.asList(args));
                    argsQueue.poll();

                    command.execute(sender, argsQueue.toArray(new String[] {}));
                    return;
                }
            }
        }

        for (ChatCommand command : commands) {
            if (command.isCommand(mainCommand)) {
                Queue<String> argsQueue = new LinkedList<String>(Arrays.asList(args));

                command.execute(sender, argsQueue.toArray(new String[] {}));

                return;
            }
        }

        if (sender instanceof Player) {
            ((Player) sender).sendMessage(ChatColor.RED + "Befehl nicht gefunden. Versuche /" + mainCommand + " help"
                    + (!subCommand.isEmpty() ? " oder /" + mainCommand + " " + subCommand : ""));
        }
    }
}
