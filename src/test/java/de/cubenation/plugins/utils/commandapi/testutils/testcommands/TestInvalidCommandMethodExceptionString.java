package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMethodExceptionString {
    public TestInvalidCommandMethodExceptionString(TestPlugin plugin) throws Exception {
    }

    @Command(main = "test", min = 1, max = 1)
    public void emptyCommand(Player player, String args) throws Exception {
        throw new Exception("test exception");
    }
}
