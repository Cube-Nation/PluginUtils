package de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandNoMax {
    private TestPlugin plugin;

    public TestValidCommandNoMax(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", max = 0)
    public void testNoMaxCommand(Player player, String[] args) {
        plugin.doSomeThing("testNoMaxCommand");
    }
}
