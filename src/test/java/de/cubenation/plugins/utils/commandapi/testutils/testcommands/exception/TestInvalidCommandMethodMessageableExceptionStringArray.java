package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;
import de.cubenation.plugins.utils.exceptionapi.MessageableException;
import de.cubenation.plugins.utils.exceptionapi.WorldNotFoundException;

public class TestInvalidCommandMethodMessageableExceptionStringArray {
    public TestInvalidCommandMethodMessageableExceptionStringArray(TestPlugin plugin) {
    }

    @Command(main = "test")
    public void emptyCommand(Player player, String[] args) throws MessageableException {
        throw new WorldNotFoundException("world");
    }
}
