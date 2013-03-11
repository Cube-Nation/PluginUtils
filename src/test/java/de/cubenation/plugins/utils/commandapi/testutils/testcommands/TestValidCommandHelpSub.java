package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpSub {
    private TestPlugin plugin;

    public TestValidCommandHelpSub(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", sub = "foo", help = "this is a sub help test")
    public void testHelpSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpSubCommand");
    }
}
