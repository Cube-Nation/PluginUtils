package de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMinMax1 {
    private TestPlugin plugin;

    public TestInvalidCommandMinMax1(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", max = 3, min = 2)
    public void testMinCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinCommand");
    }
}
