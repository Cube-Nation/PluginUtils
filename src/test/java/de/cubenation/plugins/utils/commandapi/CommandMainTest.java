package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMultiMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandOtherMethod;

public class CommandMainTest extends AbstractTest {
    @Test
    public void testOneMainCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));
    }

    @Test
    public void testMultiMainCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiMain.class);

        executeComannd("/test1");
        executeComannd("/test2");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiMainCommand"));
        Assert.assertEquals(new Short((short) 2), testValid.get("testMultiMainCommand"));
    }

    @Test
    public void testOtherCommand() throws CommandException {
        commandsManager.add(TestValidCommandOtherMethod.class);

        executeComannd("/test");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOtherCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOtherCommand"));
    }

    @Test
    public void testEmptyCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/");

        Assert.assertEquals(0, testValid.size());
    }
}
