package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.constructor.TestValidCommandParameterString;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandParameterMissing;

public class CommandParameterTest extends AbstractTest {
    @Test
    public void testMissingParameterCommand() throws CommandException {
        commandsManager.add(TestValidCommandParameterMissing.class);

        executeComannd("/test");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMissingCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testMissingCommand"));
    }

    @Test
    public void testStringParameterCommand() throws CommandException {
        commandsManager.add(TestValidCommandParameterString.class);

        executeComannd("/test 5");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testStringCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testStringCommand"));
    }
}
