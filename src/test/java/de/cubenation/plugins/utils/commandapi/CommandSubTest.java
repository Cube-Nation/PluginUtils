package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMainMultiSub;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMainSub;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMultiMainMultiSub;

public class CommandSubTest extends AbstractTest {
    @Test
    public void testOneMainOneSubCommand() throws CommandException {
        commandsManager.add(TestValidCommandMainSub.class);

        executeComannd("/test bla");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainOneSubCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainOneSubCommand"));
    }

    @Test
    public void testOneMainMultiSubCommand() throws CommandException {
        commandsManager.add(TestValidCommandMainMultiSub.class);

        executeComannd("/test bla1");
        executeComannd("/test bla2");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainMultiSubCommand"));
        Assert.assertEquals(new Short((short) 2), testValid.get("testOneMainMultiSubCommand"));
    }

    @Test
    public void testMultiMainMultiSubCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiMainMultiSub.class);

        executeComannd("/test1 bla1");
        executeComannd("/test1 bla2");
        executeComannd("/test2 bla1");
        executeComannd("/test2 bla2");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiMainMultiSubCommand"));
        Assert.assertEquals(new Short((short) 4), testValid.get("testMultiMainMultiSubCommand"));
    }
}
