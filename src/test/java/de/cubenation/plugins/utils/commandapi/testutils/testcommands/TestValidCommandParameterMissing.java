package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandParameterMissing {
    private TestPlugin plugin;

    public TestValidCommandParameterMissing(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 0, max = 0)
    public void testMissingCommand(Player player) {
        plugin.doSomeThing("testMissingCommand");
    }
}
