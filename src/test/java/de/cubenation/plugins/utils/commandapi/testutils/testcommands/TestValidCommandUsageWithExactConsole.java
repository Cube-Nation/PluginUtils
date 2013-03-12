package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.ConsoleCommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandUsageWithExactConsole {
    private TestPlugin plugin;

    public TestValidCommandUsageWithExactConsole(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "gamemode", usage = "[mode] [player]", min = 0, max = 0)
    @SenderConsole
    public void testUsageExactCommand(ConsoleCommandSender sender, String[] args) {
        plugin.doSomeThing("testUsageExactCommand");
    }
}
