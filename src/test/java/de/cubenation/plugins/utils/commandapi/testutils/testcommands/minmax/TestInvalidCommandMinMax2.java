package de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMinMax2 {
    private TestPlugin plugin;

    public TestInvalidCommandMinMax2(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", max = 4, min = 3)
    public void testMaxCommand(Player player, String[] args) {
        plugin.doSomeThing("testMaxCommand");
    }
}
