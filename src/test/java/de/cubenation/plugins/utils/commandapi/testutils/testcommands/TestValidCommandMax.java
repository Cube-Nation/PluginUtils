package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMax {
    private TestPlugin plugin;

    public TestValidCommandMax(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", max = 1)
    public void testMaxCommand(Player player, String[] args) {
        plugin.doSomeThing("testMaxCommand");
    }
}
