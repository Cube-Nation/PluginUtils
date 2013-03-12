package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelp {
    private TestPlugin plugin;

    public TestValidCommandHelp(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", help = "this is a help test")
    public void testHelpCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpCommand");
    }
}
