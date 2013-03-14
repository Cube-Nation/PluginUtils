package de.cubenation.plugins.utils.commandapi.testutils.testcommands.help;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpWorld {
    private TestPlugin plugin;

    public TestValidCommandHelpWorld(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", help = "this is a help test")
    @World("test1")
    public void testHelpCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpCommand");
    }
}
