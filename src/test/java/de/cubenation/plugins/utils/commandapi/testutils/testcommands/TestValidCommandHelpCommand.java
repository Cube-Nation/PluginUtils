package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpCommand {
    private TestPlugin plugin;

    public TestValidCommandHelpCommand(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", sub = "help")
    public void testHelpCommandCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpCommandCommand");
    }
}