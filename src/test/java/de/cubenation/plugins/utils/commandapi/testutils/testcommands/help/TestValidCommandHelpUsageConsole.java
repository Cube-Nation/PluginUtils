package de.cubenation.plugins.utils.commandapi.testutils.testcommands.help;

import org.bukkit.command.ConsoleCommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandHelpUsageConsole {
    private TestPlugin plugin;

    public TestValidCommandHelpUsageConsole(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "time", sub = "set", help = "this is a help test", usage = "[TIME]", min = 1, max = 1)
    @SenderConsole
    public void testHelpCommand(ConsoleCommandSender sender, String[] args) {
        plugin.doSomeThing("testHelpCommand");
    }
}
