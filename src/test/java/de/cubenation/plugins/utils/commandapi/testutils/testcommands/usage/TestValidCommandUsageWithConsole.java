package de.cubenation.plugins.utils.commandapi.testutils.testcommands.usage;

import org.bukkit.command.ConsoleCommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandUsageWithConsole {
    private TestPlugin plugin;

    public TestValidCommandUsageWithConsole(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "gamemode", usage = "[mode] [player]", min = 2, max = 2)
    @SenderConsole
    public void testUsageCommand(ConsoleCommandSender sender, String[] args) {
        plugin.doSomeThing("testUsageCommand");
    }
}
