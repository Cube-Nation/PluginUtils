package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMethodExceptionNoParameter {
    public TestInvalidCommandMethodExceptionNoParameter(TestPlugin plugin) throws Exception {
    }

    @Command(main = "test", min = 0, max = 0)
    public void emptyCommand(Player player) throws Exception {
        throw new Exception("test exception");
    }
}
