package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMainSub {
    private TestPlugin plugin;

    public TestValidCommandMainSub(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", sub = "bla")
    public void testOneMainOneSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneMainOneSubCommand");
    }
}
