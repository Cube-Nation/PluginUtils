package de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMinMaxOk {
    private TestPlugin plugin;

    public TestInvalidCommandMinMaxOk(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test1", max = 0, min = 0)
    public void testEmptyCommand(Player player) {
        plugin.doSomeThing("testEmptyCommand");
    }

    @Command(main = "test", max = 1, min = 1)
    public void testOneCommand(Player player, String args) {
        plugin.doSomeThing("testOneCommand");
    }

    @Command(main = "test", max = 6, min = 5)
    public void testMinCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinCommand");
    }

    @Command(main = "test", max = 4, min = 3)
    public void testMaxCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinCommand");
    }
}
