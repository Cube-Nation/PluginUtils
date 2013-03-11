package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMultiMain;

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
}
