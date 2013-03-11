package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMinMaxRange {
    private TestPlugin plugin;

    public TestValidCommandMinMaxRange(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", min = 1, max = 2)
    public void testMinMaxRangeCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinMaxRangeCommand");
    }
}
