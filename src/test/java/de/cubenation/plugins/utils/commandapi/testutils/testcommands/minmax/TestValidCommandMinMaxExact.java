package de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMinMaxExact {
    private TestPlugin plugin;

    public TestValidCommandMinMaxExact(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 1, max = 1)
    public void testMinMaxExactCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinMaxExactCommand");
    }
}
