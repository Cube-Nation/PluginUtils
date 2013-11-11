package de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMainSubMulti {
    private TestPlugin plugin;

    public TestValidCommandMainSubMulti(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", sub = "one", help = "this is a help test 1")
    public void testOneMainOneSubCommand1(Player player, String[] args) {
        plugin.doSomeThing("testOneMainOneSubCommand1");
    }

    @Command(main = "test", sub = "two", help = "this is a help test 2")
    public void testOneMainOneSubCommand2(Player player, String[] args) {
        plugin.doSomeThing("testOneMainOneSubCommand2");
    }
}
