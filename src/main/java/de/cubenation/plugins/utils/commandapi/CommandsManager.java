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
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.annotation.SenderPlayer;
import de.cubenation.plugins.utils.commandapi.annotation.SenderRemoteConsole;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;

public class CommandsManager {
    private Object[] constructorParameter;
    private ArrayList<ChatCommand> commands = new ArrayList<ChatCommand>();
    private PermissionInterface permissionInterface = null;

    public CommandsManager(Object... constructorParameter) {
        this.constructorParameter = constructorParameter;
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
                throw new CommandWarmUpException("constructor parameter could not be null");
            }
        }

        try {
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
            Method[] declaredMethods = instance.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                boolean annotationPresent = declaredMethod.isAnnotationPresent(Command.class);
                if (annotationPresent) {
                    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();

                    if (parameterTypes.length != 2) {
                        throw new CommandWarmUpException(commandClass, "wrong number of paramter in method " + declaredMethod.getName()
                                + ", expected 2 but was " + parameterTypes.length);
                    }
                    if (!parameterTypes[0].equals(Player.class) && !parameterTypes[0].equals(ConsoleCommandSender.class)
                            && !parameterTypes[0].equals(BlockCommandSender.class) && !parameterTypes[0].equals(RemoteConsoleCommandSender.class)
                            && !parameterTypes[0].equals(CommandSender.class)) {
                        throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                                + Player.class.getSimpleName() + ", " + ConsoleCommandSender.class.getSimpleName() + ", "
                                + BlockCommandSender.class.getSimpleName() + ", " + RemoteConsoleCommandSender.class.getSimpleName() + " or "
                                + CommandSender.class.getSimpleName() + " but was " + parameterTypes[0].getName());
                    }

                    if (!parameterTypes[1].equals(String[].class)) {
                        throw new CommandWarmUpException(commandClass, "second parameter in method " + declaredMethod.getName() + " must be String[] but was "
                                + parameterTypes[1].getName());
                    }

                    short annoCount = 0;
                    if (declaredMethod.isAnnotationPresent(SenderPlayer.class)) {
                        if (!parameterTypes[0].equals(Player.class) && !parameterTypes[0].equals(CommandSender.class)) {
                            throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                                    + Player.class.getSimpleName() + " or " + CommandSender.class.getSimpleName() + " but was " + parameterTypes[0].getName());
                        }
                        annoCount++;
                    }
                    if (declaredMethod.isAnnotationPresent(SenderConsole.class)) {
                        if (!parameterTypes[0].equals(ConsoleCommandSender.class) && !parameterTypes[0].equals(CommandSender.class)) {
                            throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                                    + ConsoleCommandSender.class.getSimpleName() + " or " + CommandSender.class.getSimpleName() + " but was "
                                    + parameterTypes[0].getName());
                        }
                        annoCount++;
                    }
                    if (declaredMethod.isAnnotationPresent(SenderBlock.class)) {
                        if (!parameterTypes[0].equals(BlockCommandSender.class) && !parameterTypes[0].equals(CommandSender.class)) {
                            throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                                    + BlockCommandSender.class.getSimpleName() + " or " + CommandSender.class.getSimpleName() + " but was "
                                    + parameterTypes[0].getName());
                        }
                        annoCount++;
                    }
                    if (declaredMethod.isAnnotationPresent(SenderRemoteConsole.class)) {
                        if (!parameterTypes[0].equals(RemoteConsoleCommandSender.class) && !parameterTypes[0].equals(CommandSender.class)) {
                            throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                                    + RemoteConsoleCommandSender.class.getSimpleName() + " or " + CommandSender.class.getSimpleName() + " but was "
                                    + parameterTypes[0].getName());
                        }
                        annoCount++;
                    }

                    if (annoCount > 1 && !parameterTypes[0].equals(CommandSender.class)) {
                        throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                                + CommandSender.class.getSimpleName() + " but was " + parameterTypes[0].getName());
                    }

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
