package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandManagerException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;

public class CommandsManager {
    private Object[] constructorParameter = new Object[] {};
    private ArrayList<ChatCommand> commands = new ArrayList<ChatCommand>();
    private PermissionInterface permissionInterface = null;
    private CommandValidator commandValidator = new CommandValidator();

    public CommandsManager(Object... constructorParameter) throws CommandManagerException {
        this.constructorParameter = constructorParameter;

        for (Object parameter : constructorParameter) {
            if (parameter == null) {
                throw new CommandManagerException("manager constructor parameter could not be null");
            }
        }
    }

    public void add(Class<?> commandClass) throws CommandWarmUpException {
        add(commandClass, constructorParameter);
    }

    public void add(Class<?> commandClass, Object... constructorParameter) throws CommandWarmUpException {
        if (commandClass == null) {
            throw new CommandWarmUpException("command class could not be null");
        }

        for (Object parameter : constructorParameter) {
            if (parameter == null) {
                throw new CommandWarmUpException("command constructor parameter could not be null");
            }
        }

        try {
            // create object instance
            Object instance = null;
            try {
                List<Object> objectList = Arrays.asList(constructorParameter);
                List<Class<?>> classList = new ArrayList<Class<?>>();

                for (Object object : objectList) {
                    Class<? extends Object> classObj = object.getClass();

                    // check inline classes, to get super class
                    Class<?> topLevel = classObj.getEnclosingClass();
                    if (topLevel != null) {
                        classObj = classObj.getSuperclass();
                    }

                    classList.add(classObj);
                }

                Constructor<?> ctor = commandClass.getConstructor(classList.toArray(new Class<?>[] {}));
                instance = ctor.newInstance(constructorParameter);
            } catch (NoSuchMethodException e) {
                try {
                    instance = commandClass.newInstance();
                } catch (InstantiationException e1) {
                    throw new CommandWarmUpException(commandClass,
                            "no matching constructor found, matches are empty constructors and constructors is specified in add() or CommandsManager()");
                }
            }

            // load object methods for commands
            Method[] declaredMethods = instance.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                boolean annotationPresent = declaredMethod.isAnnotationPresent(Command.class);
                if (annotationPresent) {
                    commandValidator.validate(commandClass, declaredMethod);

                    ChatCommand newchatCommand = new ChatCommand(instance, declaredMethod);
                    if (permissionInterface != null) {
                        newchatCommand.setPermissionInterface(permissionInterface);
                    }
                    commands.add(newchatCommand);
                }
            }
        } catch (CommandWarmUpException e) {
            throw e;
        } catch (Exception e) {
            throw new CommandWarmUpException(commandClass, "error on command warmup", e);
        }
    }

    public void clear() {
        commands.clear();
    }

    public void execute(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) throws CommandException {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender) && !(sender instanceof BlockCommandSender)
                && !(sender instanceof RemoteConsoleCommandSender)) {
            throw new CommandException(CommandSender.class.getSimpleName() + " " + sender.getClass().getName() + " not supported");
        }

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
                if (command.isCommand(sender, mainCommand, "help") || command.isCommand(sender, mainCommand, "?")) {
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
                if (command.isCommand(sender, mainCommand, subCommand)) {
                    Queue<String> argsQueue = new LinkedList<String>(Arrays.asList(args));
                    argsQueue.poll();
                    command.execute(sender, argsQueue.toArray(new String[] {}));
                    return;
                }
            }
        }

        for (ChatCommand command : commands) {
            if (command.isCommand(sender, mainCommand)) {
                Queue<String> argsQueue = new LinkedList<String>(Arrays.asList(args));

                command.execute(sender, argsQueue.toArray(new String[] {}));
                return;
            }
        }

        if (sender instanceof Player) {
            ((Player) sender).sendMessage(ChatColor.RED + "Befehl nicht gefunden. Versuche /" + mainCommand + " help"
                    + (!subCommand.isEmpty() ? " oder /" + mainCommand + " " + subCommand + " help" : ""));
        } else {
            sender.sendMessage("Befehl nicht gefunden. Versuche /" + mainCommand + " help"
                    + (!subCommand.isEmpty() ? " oder /" + mainCommand + " " + subCommand + " help" : ""));
        }
    }

    public void setPermissionInterface(PermissionInterface permissionInterface) {
        this.permissionInterface = permissionInterface;

        for (ChatCommand command : commands) {
            command.setPermissionInterface(permissionInterface);
        }
    }
}
