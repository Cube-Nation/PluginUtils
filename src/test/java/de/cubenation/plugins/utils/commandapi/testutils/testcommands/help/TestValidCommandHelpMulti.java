package de.cubenation.plugins.utils.commandapi.testutils.testcommands.help;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpMulti {
    private TestPlugin plugin;

    public TestValidCommandHelpMulti(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = { "test1", "test2" }, sub = { "foo", "bar" }, help = "this is a help test")
    public void testHelpMultiCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpMultiCommand");
    }
}
