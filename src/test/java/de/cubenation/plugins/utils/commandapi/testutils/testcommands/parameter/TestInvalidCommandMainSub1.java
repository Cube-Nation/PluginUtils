package de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandMainSub1 {
    private TestPlugin plugin;

    public TestInvalidCommandMainSub1(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    public void testOneMainOneSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneMainOneSubCommand");
    }

    @Command(main = "test", sub = "bla")
    public void testTwoMainOneSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testTwoMainOneSubCommand");
    }
}
