package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;
import de.cubenation.plugins.utils.exceptionapi.MessageableException;
import de.cubenation.plugins.utils.exceptionapi.WorldNotFoundException;

public class TestInvalidCommandMethodMessageableExceptionNoParameter {
    public TestInvalidCommandMethodMessageableExceptionNoParameter(TestPlugin plugin) throws Exception {
    }

    @Command(main = "test", min = 0, max = 0)
    public void emptyCommand(Player player) throws MessageableException {
        throw new WorldNotFoundException("world");
    }
}
