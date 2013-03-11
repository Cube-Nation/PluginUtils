package de.cubenation.plugins.utils.commandapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestInvalidCommandEmptyMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestInvalidCommandOtherException;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestInvalidCommandWrongConstructor;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestInvalidCommandWrongMinMax;

public class CommandExceptionTest {
    @Test
    public void testCommandNull() {
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
    public void testCommandMainNull() {
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
    public void testCommandWrongConstructor() {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongConstructor.class);
            Assert.fail("expected wrong constructor");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongConstructor.class.getName() + "] no matching constructor found", e.getMessage());
        }
    }

    @Test
    public void testCommandOtherException() {
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
    public void testCommandWrongMinMax() {
        CommandsManager commandsManager = new CommandsManager(new JavaPlugin() {
        });

        try {
            commandsManager.add(TestInvalidCommandWrongMinMax.class);
            Assert.fail("expected other exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWrongMinMax.class.getName() + "] min(2) attribute could not be greater than max(1) attribute",
                    e.getMessage());
        }
    }
}
