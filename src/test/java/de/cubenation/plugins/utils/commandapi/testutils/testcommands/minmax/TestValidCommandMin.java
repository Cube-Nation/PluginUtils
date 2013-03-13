package de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMin {
    private TestPlugin plugin;

    public TestValidCommandMin(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 1)
    public void testMinCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinCommand");
    }
}
