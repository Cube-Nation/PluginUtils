package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Method;
import java.util.ArrayList;

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
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;

public class CommandValidator {
    public void validate(Class<?> commandClass, Method declaredMethod) throws CommandWarmUpException {
        validateParameterArguments(commandClass, declaredMethod);
        validateParameterSender(commandClass, declaredMethod);
        validateMinMax(commandClass, declaredMethod);
    }

    private void validateMinMax(Class<?> commandClass, Method declaredMethod) throws CommandWarmUpException {
        Command commandAnno = declaredMethod.getAnnotation(Command.class);

        if (commandAnno.min() < 0) {
            throw new CommandWarmUpException("min(" + commandAnno.min() + ") attribute could not be smaller than 0");
        }

        if (commandAnno.max() >= 0 && commandAnno.min() > commandAnno.max()) {
            throw new CommandWarmUpException("min(" + commandAnno.min() + ") attribute could not be greater than max(" + commandAnno.max() + ") attribute");
        }
    }

    private void validateParameterArguments(Class<?> commandClass, Method declaredMethod) throws CommandWarmUpException {
        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
        Command commandAnno = declaredMethod.getAnnotation(Command.class);

        // check method parameter count and types
        if (parameterTypes.length == 1) { // only one parameter
            if (commandAnno.min() != 0 || commandAnno.max() != 0) {
                throw new CommandWarmUpException(commandClass, "wrong number of paramter in method " + declaredMethod.getName() + ", expected 2 but was "
                        + parameterTypes.length);
            }
        } else if (parameterTypes.length == 2) { // String && String []
            if (!parameterTypes[1].equals(String.class) && !parameterTypes[1].equals(String[].class)) {
                throw new CommandWarmUpException(commandClass, "wrong type of paramter in method " + declaredMethod.getName()
                        + ", expected String or String[] but was " + parameterTypes[1].getName());
            }

            // check String[] expected
            if (parameterTypes[1].equals(String.class) && (commandAnno.max() == -1 || commandAnno.max() > 1 || commandAnno.min() > 2 || commandAnno.min() == 0)) {
                throw new CommandWarmUpException(commandClass, "wrong type of paramter in method " + declaredMethod.getName() + ", expected String[] but was "
                        + parameterTypes[1].getName());
            }
        } else {
            throw new CommandWarmUpException(commandClass, "wrong number of paramter in method " + declaredMethod.getName() + ", expected 1-2 but was "
                    + parameterTypes.length);
        }
    }

    private void validateParameterSender(Class<?> commandClass, Method declaredMethod) throws CommandWarmUpException {
        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();

        if (!parameterTypes[0].equals(Player.class) && !parameterTypes[0].equals(ConsoleCommandSender.class)
                && !parameterTypes[0].equals(BlockCommandSender.class) && !parameterTypes[0].equals(RemoteConsoleCommandSender.class)
                && !parameterTypes[0].equals(CommandSender.class)) {
            throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be " + Player.class.getSimpleName()
                    + ", " + ConsoleCommandSender.class.getSimpleName() + ", " + BlockCommandSender.class.getSimpleName() + ", "
                    + RemoteConsoleCommandSender.class.getSimpleName() + " or " + CommandSender.class.getSimpleName() + " but was "
                    + parameterTypes[0].getName());
        }

        short annoCount = 0;
        if (declaredMethod.isAnnotationPresent(SenderPlayer.class)) {
            annoCount++;
        }
        if (declaredMethod.isAnnotationPresent(SenderConsole.class)) {
            annoCount++;
        }
        if (declaredMethod.isAnnotationPresent(SenderBlock.class)) {
            annoCount++;
        }
        if (declaredMethod.isAnnotationPresent(SenderRemoteConsole.class)) {
            annoCount++;
        }

        if (annoCount > 1 && !parameterTypes[0].equals(CommandSender.class)) {
            throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                    + CommandSender.class.getSimpleName() + " cause of multi annotations are found but was " + parameterTypes[0].getName());
        }

        if (annoCount == 0 || declaredMethod.isAnnotationPresent(SenderPlayer.class)) {
            if (!parameterTypes[0].equals(Player.class) && !parameterTypes[0].equals(CommandSender.class)) {
                throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                        + Player.class.getSimpleName() + " but was " + parameterTypes[0].getName());
            }
        }

        if (declaredMethod.isAnnotationPresent(SenderConsole.class)) {
            if (!parameterTypes[0].equals(ConsoleCommandSender.class) && !parameterTypes[0].equals(CommandSender.class)) {
                throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                        + ConsoleCommandSender.class.getSimpleName() + " but was " + parameterTypes[0].getName());
            }
        }
        if (declaredMethod.isAnnotationPresent(SenderBlock.class)) {
            if (!parameterTypes[0].equals(BlockCommandSender.class) && !parameterTypes[0].equals(CommandSender.class)) {
                throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                        + BlockCommandSender.class.getSimpleName() + " but was " + parameterTypes[0].getName());
            }
        }
        if (declaredMethod.isAnnotationPresent(SenderRemoteConsole.class)) {
            if (!parameterTypes[0].equals(RemoteConsoleCommandSender.class) && !parameterTypes[0].equals(CommandSender.class)) {
                throw new CommandWarmUpException(commandClass, "first parameter in method " + declaredMethod.getName() + " must be "
                        + RemoteConsoleCommandSender.class.getSimpleName() + " but was " + parameterTypes[0].getName());
            }
        }
    }

    public void checkSimilar(ChatCommand existingChatCommand, ChatCommand newChatCommand) throws CommandWarmUpException {
        // check sender
        if (!existingChatCommand.getSenderType().equals(newChatCommand.getSenderType())) {
            return;
        }

        // check main
        boolean equalMainFound = false;
        for (String main : newChatCommand.getMainAliases()) {
            if (existingChatCommand.getMainAliases().contains(main)) {
                equalMainFound = true;
                break;
            }
        }

        if (!equalMainFound) {
            return;
        }

        // check sub
        boolean equalSubFound = false;
        if (!newChatCommand.getSubAliases().isEmpty() || !existingChatCommand.getSubAliases().isEmpty()) {
            for (String sub : newChatCommand.getSubAliases()) {
                if (existingChatCommand.getSubAliases().contains(sub)) {
                    equalSubFound = true;
                    break;
                }
            }
        } else {
            equalSubFound = true;
        }

        if (!equalSubFound) {
            return;
        }

        // check world
        boolean equalWorldFound = false;
        if (!existingChatCommand.getWorlds().isEmpty() && !newChatCommand.getWorlds().isEmpty()) {
            for (String world : newChatCommand.getWorlds()) {
                if (existingChatCommand.getWorlds().contains(world)) {
                    equalWorldFound = true;
                    break;
                }
            }
        } else {
            equalWorldFound = true;
        }

        if (!equalWorldFound) {
            return;
        }

        // check min/max
        if (existingChatCommand.getMinAttribute() > newChatCommand.getMaxAttribute() && newChatCommand.getMaxAttribute() >= 0) {
            return;
        }
        if (newChatCommand.getMinAttribute() > existingChatCommand.getMaxAttribute() && existingChatCommand.getMaxAttribute() >= 0) {
            return;
        }

        // check permisson
        ArrayList<String> newPermissions = newChatCommand.getPermissions();
        ArrayList<String> newPermissionsNot = newChatCommand.getPermissionsNot();
        ArrayList<String> existsPermissions = existingChatCommand.getPermissions();
        ArrayList<String> existsPermissionsNot = existingChatCommand.getPermissionsNot();
        if ((!newPermissions.isEmpty() && !existsPermissionsNot.isEmpty() && existsPermissionsNot.containsAll(newPermissions) && newPermissions
                .containsAll(existsPermissionsNot))
                || (!newPermissionsNot.isEmpty() && !existsPermissions.isEmpty() && existsPermissions.containsAll(newPermissionsNot) && newPermissionsNot
                        .containsAll(existsPermissions))) {
            return;
        }

        throw new CommandWarmUpException(newChatCommand.getInstance().getClass(), "similar command found for " + newChatCommand.getMethod().getName()
                + " in class " + existingChatCommand.getInstance().getClass().getName() + " method " + existingChatCommand.getMethod().getName());
    }

    public void checkEqual(ChatCommand existingChatCommand, ChatCommand newChatCommand) throws CommandWarmUpException {
        if (existingChatCommand == null || newChatCommand == null) {
            return;
        }

        if (existingChatCommand.getMethod().equals(newChatCommand.getMethod())) {
            throw new CommandWarmUpException(newChatCommand.getInstance().getClass(), "command already added");
        }
    }

    public void checkAsynchronSupport(ChatCommand newChatCommand) throws CommandWarmUpException {
        if (newChatCommand.isAsynchronCommand() && newChatCommand.getPlugin() == null) {
            throw new CommandWarmUpException(newChatCommand.getInstance().getClass(), "Plugin not set! For asynchron command "
                    + newChatCommand.getMethod().getName() + " the plugin must set in command manager or command");
        }
    }
}
