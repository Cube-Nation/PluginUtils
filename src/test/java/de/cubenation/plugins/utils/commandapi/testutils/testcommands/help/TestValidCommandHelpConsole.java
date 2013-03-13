package de.cubenation.plugins.utils.commandapi.testutils.testcommands.help;

import org.bukkit.command.ConsoleCommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpConsole {
    private TestPlugin plugin;

    public TestValidCommandHelpConsole(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", help = "this is a help test")
    @SenderConsole
    public void testHelpCommand(ConsoleCommandSender sender, String[] args) {
        plugin.doSomeThing("testHelpCommand");
    }
}
