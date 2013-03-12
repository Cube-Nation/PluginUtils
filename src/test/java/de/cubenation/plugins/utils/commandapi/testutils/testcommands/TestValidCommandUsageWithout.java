package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandUsageWithout {
    private TestPlugin plugin;

    public TestValidCommandUsageWithout(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "gamemode", min = 2, max = 2)
    public void testUsageCommand(Player player, String[] args) {
        plugin.doSomeThing("testUsageCommand");
    }
}
