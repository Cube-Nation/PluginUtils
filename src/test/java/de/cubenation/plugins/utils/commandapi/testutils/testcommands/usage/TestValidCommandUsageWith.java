package de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandUsageWith {
    private TestPlugin plugin;

    public TestValidCommandUsageWith(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "gamemode", usage = "[mode] [player]", min = 2, max = 2)
    public void testUsageCommand(Player player, String[] args) {
        plugin.doSomeThing("testUsageCommand");
    }
}
