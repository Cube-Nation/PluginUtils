package de.cubenation.plugins.utils.commandapi;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.exception.CommandManagerException;
import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestCustomCommandSender;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandEmptyMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandMethodExceptionNoParameter;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandMethodExceptionString;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandMethodExceptionStringArray;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandMultiAnnotation;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandOtherException;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongConstructor;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterFirstBlock;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterFirstConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterFirstParameter;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterFirstPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterFirstRemoteConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterNumber;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterSecond;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterSecondString;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterWithoutFirst;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMethodParameterWithoutSecond;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMin;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandWrongMinMax;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMain;

public class CommandExceptionTest extends AbstractTest {
    @Test
    public void testCommandNull() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(null);
            Assert.fail("expected null command object raise exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("command class could not be null", e.getMessage());
        }
    }

    @Test
    public void testCommandMainNull() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandEmptyMain.class);
            Assert.fail("expected null main string raise exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandEmptyMain.class.getName() + "] main attribute could not be empty", e.getMessage());
        }
    }

    @Test
    public void testCommandWrongConstructor() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongConstructor.class);
            Assert.fail("expected wrong constructor");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongConstructor.class.getName()
                    + "] no matching constructor found, matches are empty constructors and constructors is specified in add() or CommandsManager()",
                    e.getMessage());
        }
    }

    @Test
    public void testCommandWrongNumberOfMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterNumber.class);
            Assert.fail("expected wrong number of parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterNumber.class.getName()
                    + "] wrong number of paramter in method wrongCommad, expected 2 but was 1", e.getMessage());
        }
    }

    @Test
    public void testCommandWrongFirstParameterMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterFirstParameter.class);
            Assert.fail("expected wrong first parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterFirstParameter.class.getName() + "] first parameter in method wrongCommad must be "
                    + Player.class.getSimpleName() + ", " + ConsoleCommandSender.class.getSimpleName() + ", " + BlockCommandSender.class.getSimpleName() + ", "
                    + RemoteConsoleCommandSender.class.getSimpleName() + " or " + CommandSender.class.getSimpleName() + " but was " + Integer.class.getName(),
                    e.getMessage());
        }
    }

    @Test
    public void testCommandWrongSecondMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterSecond.class);
            Assert.fail("expected wrong second parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterSecond.class.getName()
                    + "] wrong type of paramter in method wrongCommad, expected String or String[] but was " + Integer.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandOtherException() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandOtherException.class);
            Assert.fail("expected other exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandOtherException.class.getName() + "] error on command warmup", e.getMessage());
        }
    }

    @Test
    public void testCommandWrongMinMax() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMinMax.class);
            Assert.fail("expected other exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("min(2) attribute could not be greater than max(1) attribute", e.getMessage());
        }
    }

    @Test
    public void testCommandWrongMin() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMin.class);
            Assert.fail("expected other exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("min(-1) attribute could not be smaller than 0", e.getMessage());
        }
    }

    @Test
    public void testCommandMultiAnnotation() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandMultiAnnotation.class);
            Assert.fail("expected wrong first parameter exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandMultiAnnotation.class.getName() + "] first parameter in method emptyCommand must be "
                    + CommandSender.class.getSimpleName() + " cause of multi annotations are found but was " + Player.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandWrongFirstConsoleMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterFirstConsole.class);
            Assert.fail("expected wrong first parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterFirstConsole.class.getName() + "] first parameter in method wrongCommad must be "
                    + ConsoleCommandSender.class.getSimpleName() + " but was " + Player.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandWrongFirstBlockMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterFirstBlock.class);
            Assert.fail("expected wrong first parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterFirstBlock.class.getName() + "] first parameter in method wrongCommad must be "
                    + BlockCommandSender.class.getSimpleName() + " but was " + Player.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandWrongFirstRemoveConsoleMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterFirstRemoteConsole.class);
            Assert.fail("expected wrong first parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals(
                    "[" + TestInvalidCommandWrongMethodParameterFirstRemoteConsole.class.getName() + "] first parameter in method wrongCommad must be "
                            + RemoteConsoleCommandSender.class.getSimpleName() + " but was " + Player.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandWrongFirstPlayerMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterFirstPlayer.class);
            Assert.fail("expected wrong first parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterFirstPlayer.class.getName() + "] first parameter in method wrongCommad must be "
                    + Player.class.getSimpleName() + " but was " + RemoteConsoleCommandSender.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandNullConstructor() {
        try {
            new CommandsManager((Object) null);
            Assert.fail("expected null command object raise exception");
        } catch (CommandManagerException e) {
            Assert.assertEquals("manager constructor parameter could not be null", e.getMessage());
        }
    }

    @Test
    public void testCommandNullParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestValidCommandMain.class, (Object) null);
            Assert.fail("expected null command object raise exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("command constructor parameter could not be null", e.getMessage());
        }
    }

    @Test
    public void testCommandCustomSender() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        TestCustomCommandSender sender = new TestCustomCommandSender() {
        };
        try {
            executeComannd("/test", sender);
        } catch (CommandException e) {
            Assert.assertEquals(CommandSender.class.getSimpleName() + " " + sender.getClass().getName() + " not supported", e.getMessage());
        }
    }

    @Test
    public void testCommandMethodExceptionStringArray() throws CommandManagerException, CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMethodExceptionStringArray.class);

        try {
            executeComannd("/test");
            Assert.fail("expected exception on execute");
        } catch (CommandException e) {
            Assert.assertEquals("[" + TestInvalidCommandMethodExceptionStringArray.class.getName() + "] error on execute emptyCommand", e.getMessage());
            Assert.assertNotNull(e.getCause());
            Assert.assertEquals(InvocationTargetException.class, e.getCause().getClass());
            Assert.assertEquals(Exception.class, ((InvocationTargetException) e.getCause()).getTargetException().getClass());
            Assert.assertEquals("test exception", ((Exception) ((InvocationTargetException) e.getCause()).getTargetException()).getMessage());
        }
        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testCommandMethodExceptionString() throws CommandManagerException, CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMethodExceptionString.class);

        try {
            executeComannd("/test 5");
            Assert.fail("expected exception on execute");
        } catch (CommandException e) {
            Assert.assertEquals("[" + TestInvalidCommandMethodExceptionString.class.getName() + "] error on execute emptyCommand", e.getMessage());
            Assert.assertNotNull(e.getCause());
            Assert.assertEquals(InvocationTargetException.class, e.getCause().getClass());
            Assert.assertEquals(Exception.class, ((InvocationTargetException) e.getCause()).getTargetException().getClass());
            Assert.assertEquals("test exception", ((Exception) ((InvocationTargetException) e.getCause()).getTargetException()).getMessage());
        }
        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testCommandMethodExceptionNoParameter() throws CommandManagerException, CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMethodExceptionNoParameter.class);

        try {
            executeComannd("/test");
            Assert.fail("expected exception on execute");
        } catch (CommandException e) {
            Assert.assertEquals("[" + TestInvalidCommandMethodExceptionNoParameter.class.getName() + "] error on execute emptyCommand", e.getMessage());
            Assert.assertNotNull(e.getCause());
            Assert.assertEquals(InvocationTargetException.class, e.getCause().getClass());
            Assert.assertEquals(Exception.class, ((InvocationTargetException) e.getCause()).getTargetException().getClass());
            Assert.assertEquals("test exception", ((Exception) ((InvocationTargetException) e.getCause()).getTargetException()).getMessage());
        }
        Assert.assertEquals(0, chatList.size());
    }

    @Test
    public void testCommandWrongSecondStringMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterSecondString.class);
            Assert.fail("expected wrong second parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterSecondString.class.getName()
                    + "] wrong type of paramter in method wrongCommad, expected String[] but was " + String.class.getName(), e.getMessage());
        }
    }

    @Test
    public void testCommandWrongMissingSecondMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterWithoutSecond.class);
            Assert.fail("expected wrong second parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterWithoutSecond.class.getName()
                    + "] wrong number of paramter in method wrongCommad, expected 2 but was 1", e.getMessage());
        }
    }

    @Test
    public void testCommandWrongMissingAllMethodParameter() throws CommandManagerException {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMethodParameterWithoutFirst.class);
            Assert.fail("expected missing parameter");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMethodParameterWithoutFirst.class.getName()
                    + "] wrong number of paramter in method wrongCommad, expected 1-2 but was 0", e.getMessage());
        }
    }

    @Test
    public void testDupplicateCommand() throws CommandManagerException, CommandWarmUpException {
        commandsManager.add(TestValidCommandMain.class);
        try {
            commandsManager.add(TestValidCommandMain.class);
            Assert.fail("expected duplicate exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestValidCommandMain.class.getName() + "] command already added", e.getMessage());
        }
        Assert.assertEquals(0, chatList.size());
    }
}
