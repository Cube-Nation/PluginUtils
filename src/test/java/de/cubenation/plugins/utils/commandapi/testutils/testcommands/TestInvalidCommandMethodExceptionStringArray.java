package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMethodExceptionStringArray {
    public TestInvalidCommandMethodExceptionStringArray(TestPlugin plugin) throws Exception {
    }

    @Command(main = "test")
    public void emptyCommand(Player player, String[] args) throws Exception {
        throw new Exception("test exception");
    }
}
