package de.cubenation.plugins.utils.commandapi.testutils.testcommands.world;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandWorld {
    private TestPlugin plugin;

    public TestInvalidCommandWorld(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 0, max = 0)
    @World("world")
    public void testOneWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneWorldCommand");
    }

    @Command(main = "test", min = 1, max = 1)
    @World("world")
    public void testTwoWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testTwoWorldCommand");
    }

    @Command(main = "test", min = 1, max = 1)
    @World("world1")
    public void testTwo1WorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testTwoWorldCommand");
    }

    @Command(main = "test", min = 2, max = 2)
    public void testThreeWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testThreeWorldCommand");
    }

    @Command(main = "test", min = 3, max = 3)
    public void testFourWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testFourWorldCommand");
    }

    @Command(main = "test")
    public void testFiveWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testFiveWorldCommand");
    }
}
