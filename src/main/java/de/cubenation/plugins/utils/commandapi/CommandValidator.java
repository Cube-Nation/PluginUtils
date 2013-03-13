package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.Method;

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
    public static void validate(Class<?> commandClass, Method declaredMethod) throws CommandWarmUpException {
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
}
