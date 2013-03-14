package de.cubenation.plugins.utils.commandapi.testutils.testcommands.help;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpCommand {
    private TestPlugin plugin;

    public TestValidCommandHelpCommand(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", sub = "help")
    public void testHelp1CommandCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpCommandCommand1");
    }

    @Command(main = "test", sub = "?")
    public void testHelp2CommandCommand(Player player, String[] args) {
        plugin.doSomeThing("testHelpCommandCommand2");
    }
}
