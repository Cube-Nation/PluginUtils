package de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandUsageWithExact {
    private TestPlugin plugin;

    public TestValidCommandUsageWithExact(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "gamemode", usage = "[mode] [player]", min = 0, max = 0)
    public void testUsageExactCommand(Player player, String[] args) {
        plugin.doSomeThing("testUsageExactCommand");
    }
}
