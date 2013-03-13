package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpUsage {
    private TestPlugin plugin;

    public TestValidCommandHelpUsage(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "kill", help = "this is a help test", min = 1, max = 1, usage = "[Spieler]")
    public void testHelpUsageCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpUsageCommand");
    }
}
