package de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation;

import org.bukkit.command.ConsoleCommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandConsole {
    private TestPlugin plugin;

    public TestValidCommandConsole(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @SenderConsole
    public void testConsoleCommand(ConsoleCommandSender console, String[] args) {
        plugin.doSomeThing("testConsoleCommand");
    }
}
