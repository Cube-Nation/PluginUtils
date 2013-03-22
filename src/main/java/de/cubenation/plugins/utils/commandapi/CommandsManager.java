package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.chatapi.ChatService;
import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandManagerException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.exception.NoPermissionException;
import de.cubenation.plugins.utils.permissionapi.PermissionInterface;

public class CommandsManager {
    private static ChatService chatService;
    private Object[] constructorParameter = new Object[] {};
    private ArrayList<ChatCommand> commands = new ArrayList<ChatCommand>();
    private PermissionInterface permissionInterface = null;
    private ErrorHandler errorHandler = null;
    private CommandValidator commandValidator = new CommandValidator();
    private JavaPlugin plugin;

    public CommandsManager(Object... constructorParameter) throws CommandManagerException {
        this.constructorParameter = constructorParameter;

        for (Object parameter : constructorParameter) {
            if (parameter == null) {
                throw new CommandManagerException("manager constructor parameter could not be null");
            }
            if (parameter instanceof JavaPlugin) {
                plugin = (JavaPlugin) parameter;
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
            JavaPlugin localPlugin = plugin;
            try {
                List<Object> objectList = Arrays.asList(constructorParameter);
                List<Class<?>> classList = new ArrayList<Class<?>>();

                for (Object object : objectList) {
                    Class<? extends Object> classObj = object.getClass();

                    if (object instanceof JavaPlugin) {
                        localPlugin = (JavaPlugin) object;
                    }

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

                    ChatCommand newChatCommand = new ChatCommand(instance, declaredMethod, chatService);
                    if (permissionInterface != null) {
                        newChatCommand.setPermissionInterface(permissionInterface);
                    }
                    if (errorHandler != null) {
                        newChatCommand.setErrorHandler(errorHandler);
                    }
                    if (localPlugin != null) {
                        newChatCommand.setPlugin(localPlugin);
                    }

                    // check for duplicate commands
                    for (ChatCommand command : commands) {
                        commandValidator.checkEqual(command, newChatCommand);
                        commandValidator.checkSimilar(command, newChatCommand);
                    }
                    commandValidator.checkAsynchronSupport(newChatCommand);

                    commands.add(newChatCommand);
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
        String oldHelpSubCommand = "";
        boolean helpCommand = false;

        if (mainCommand.isEmpty()) {
            return;
        }

        Queue<String> argsQueue = new LinkedList<String>(Arrays.asList(args));
        Queue<String> originalArgsQueue = new LinkedList<String>(argsQueue);

        // get sub command
        if (argsQueue.size() > 0) {
            subCommand = argsQueue.poll();

            // is help for sub command
            if (argsQueue.size() > 0 && (argsQueue.peek().equalsIgnoreCase("help") || argsQueue.peek().equals("?"))) {
                oldHelpSubCommand = argsQueue.peek();
                subCommand = "";
                helpCommand = true;
            }
        }

        // is sub command help identifier
        if (!subCommand.isEmpty() && (subCommand.equalsIgnoreCase("help") || subCommand.equals("?"))) {
            oldHelpSubCommand = subCommand;
            subCommand = "";
            helpCommand = true;
        }

        // search for defined help command
        boolean definedHelpFount = false;
        if (helpCommand) {
            for (ChatCommand command : commands) {
                if (command.isCommandWithoutMinMaxWithoutWorld(sender, mainCommand, oldHelpSubCommand)) {
                    definedHelpFount = true;
                    if (subCommand.isEmpty()) {
                        subCommand = oldHelpSubCommand;
                    }
                    break;
                }
            }
        }

        if (!definedHelpFount && helpCommand) {
            // send help for all commands
            boolean helpFound = false;
            boolean permissionException = false;
            for (ChatCommand command : commands) {
                try {
                    command.sendHelp(mainCommand, subCommand, sender);
                    helpFound = true;
                } catch (NoPermissionException e) {
                    permissionException = true;
                }
            }

            if (!helpFound && permissionException) {
                chatService.one(sender, "all.noPermission");
                return;
            } else if (helpFound) {
                return;
            }
        }

        ChatCommand foundCommand = null;
        boolean withSub = true;

        // search exact one command with sub command
        for (ChatCommand command : commands) {
            if (command.isExactCommand(sender, mainCommand, subCommand, argsQueue.size())) {
                foundCommand = command;
                break;
            }
        }

        // search exact one command without sub command
        if (foundCommand == null) {
            for (ChatCommand command : commands) {
                if (command.isExactCommand(sender, mainCommand, "", originalArgsQueue.size())) {
                    foundCommand = command;
                    withSub = false;
                    break;
                }
            }
        }

        // search command without worlds with sub command
        if (foundCommand == null) {
            for (ChatCommand command : commands) {
                if (command.isCommandWithoutWorlds(sender, mainCommand, subCommand, argsQueue.size())) {
                    foundCommand = command;
                    break;
                }
            }
        }

        // search command without worlds without sub command
        if (foundCommand == null) {
            for (ChatCommand command : commands) {
                if (command.isCommandWithoutWorlds(sender, mainCommand, "", originalArgsQueue.size())) {
                    foundCommand = command;
                    withSub = false;
                    break;
                }
            }
        }

        // search command without worlds and min/max, with sub command
        if (foundCommand == null) {
            for (ChatCommand command : commands) {
                if (command.isCommandWithoutMinMaxWithoutWorld(sender, mainCommand, subCommand)) {
                    foundCommand = command;
                    break;
                }
            }
        }

        // search command without worlds and min/max, without sub command
        if (foundCommand == null) {
            for (ChatCommand command : commands) {
                if (command.isCommandWithoutMinMaxWithoutWorld(sender, mainCommand, "")) {
                    foundCommand = command;
                    withSub = false;
                    break;
                }
            }
        }

        // execute command or fail
        if (foundCommand != null) {
            if (withSub) {
                foundCommand.execute(sender, argsQueue.toArray(new String[] {}));
            } else {
                foundCommand.execute(sender, originalArgsQueue.toArray(new String[] {}));
            }
        } else {
            if (sender instanceof Player) {
                if (!subCommand.isEmpty()) {
                    chatService.one(sender, "player.commandNotFoundSub", mainCommand, subCommand);
                } else {
                    chatService.one(sender, "player.commandNotFound", mainCommand);
                }
            } else {
                if (!subCommand.isEmpty()) {
                    chatService.one(sender, "other.commandNotFoundSub", mainCommand, subCommand);
                } else {
                    chatService.one(sender, "other.commandNotFound", mainCommand);
                }
            }
        }
    }

    public void setPermissionInterface(PermissionInterface permissionInterface) {
        this.permissionInterface = permissionInterface;

        for (ChatCommand command : commands) {
            command.setPermissionInterface(permissionInterface);
        }
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;

        for (ChatCommand command : commands) {
            command.setErrorHandler(errorHandler);
        }
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;

        for (ChatCommand command : commands) {
            command.setPlugin(plugin);
        }
    }

    public static void setOwnChatService(ChatService chatService) {
        CommandsManager.chatService = chatService;
    }
}
