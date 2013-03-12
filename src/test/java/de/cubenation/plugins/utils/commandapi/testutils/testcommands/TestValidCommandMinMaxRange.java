package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMinMaxRange {
    private TestPlugin plugin;

    public TestValidCommandMinMaxRange(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 1, max = 2)
    public void testMinMaxRangeCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinMaxRangeCommand");
    }
}
