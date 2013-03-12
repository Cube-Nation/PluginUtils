package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpSub {
    private TestPlugin plugin;

    public TestValidCommandHelpSub(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", sub = "foo", help = "this is a sub help test")
    public void testHelpSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpSubCommand");
    }
}
