package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandUsageWith {
    private TestPlugin plugin;

    public TestValidCommandUsageWith(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "gamemode", usage = "[mode] [player]", min = 2, max = 2)
    public void testUsageCommand(Player player, String[] args) {
        plugin.doSomeThing("testUsageCommand");
    }
}
