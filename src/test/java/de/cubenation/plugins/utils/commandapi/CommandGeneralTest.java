package de.cubenation.plugins.utils.commandapi;

import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.GeneralTestCommand;

public class CommandGeneralTest extends AbstractTest {
    @Test
    public void testGeneralCommand() throws CommandWarmUpException {
        commandsManager.add(GeneralTestCommand.class);
    }
}
